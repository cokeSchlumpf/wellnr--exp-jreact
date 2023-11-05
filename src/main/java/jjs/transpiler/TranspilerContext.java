package jjs.transpiler;

import com.wellnr.commons.Operators;
import com.wellnr.commons.ReflectionOperators;
import com.wellnr.commons.functions.Function0;
import javassist.util.proxy.ProxyFactory;
import jjs.Before;
import jjs.ast.*;
import jjs.lang.expressions.*;
import jjs.lang.expressions.types.*;
import jjs.lang.expressions.types.Expression;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public final class TranspilerContext {

    public static final TranspilerContext instance = new TranspilerContext();

    private final Stack<ArrayList<Statement>> program = new Stack<>();

    private int variableCounter = 0;

    public static int getAndUpdateVariableCounter() {
        return ++(getInstance().variableCounter);
    }

    public static ArrayList<Statement> getBody() {
        return getInstance().program.peek();
    }

    public static TranspilerContext getInstance() {
        return instance;
    }

    public static Stack<ArrayList<Statement>> getStack() {
        return getInstance().program;
    }

    /**
     * Returns the list of functions which should be transpiled to JavaScript methods.
     *
     * @param clazz The class to analyze.
     * @return A list of functions/ methods.
     */
    public List<Method> getJJSFunctions(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
            .filter(method -> !method.getName().equals("main"))
            .filter(method -> !method.getName().startsWith("lambda$"))
            .filter(method -> Objects.isNull(method.getAnnotation(Before.class)))
            .filter(method -> method.getReturnType()
                .equals(void.class) || Any.class.isAssignableFrom(method.getReturnType())
            )
            .toList();
    }

    public List<Statement> recordBody(Function0<Object> body, boolean allowReturn) {
        this.program.push(new ArrayList<>());

        var result = Operators.suppressExceptions(body::get);

        if (Objects.nonNull(result) && result instanceof Expression exp) {
            if (allowReturn) {
                this.program.peek().add(ReturnStatement.apply(exp.transpile()));
            } else {
                this.program.peek().add(ExpressionStatement.apply(exp.transpile()));
            }
        }

        return this.program.pop();
    }

    /**
     * Records a method call of a function/ method which should be transpiled to JavaScript.
     *
     * @param jjsFunction The function/ method to be transpiled.
     * @return A FunctionDeclaration instance representing the JavaScript function.
     */
    public FunctionDeclaration recordMethod(Method jjsFunction, Object obj) {
        jjsFunction.setAccessible(true);

        var arguments = createMethodParameters(jjsFunction);
        var proxy = this.createRecordingProxy(jjsFunction, obj.getClass());

        copyFields(obj, proxy, obj.getClass());
        var body = recordBody(() -> jjsFunction.invoke(proxy, arguments), true);
        copyFields(proxy, obj, obj.getClass());


        // Create function declaration
        var name = jjsFunction.getName();
        var parameters = Arrays
            .stream(jjsFunction.getParameters())
            .map(p -> (VariableDeclaratorId) Identifier.apply(p.getName()))
            .toList();

        return FunctionDeclaration.apply(
            Identifier.apply(name), parameters, body
        );
    }

    /**
     * Copies values for all fields (including private and superclass fields) from the source object to the target
     * object.
     *
     * @param source The source object.
     * @param target The targe object.
     * @param clazz  The class of both objects.
     */
    private void copyFields(Object source, Object target, Class<?> clazz) {
        var fields = ReflectionOperators.getAllFields(clazz)
            .stream()
            .filter(f -> !Modifier.isStatic(f.getModifiers()))
            .filter(f -> !Modifier.isFinal(f.getModifiers()))
            .toList();

        for (var field : fields) {
            try {
                field.setAccessible(true);
                field.set(target, field.get(source));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * This method takes the parameters of a JJS function and transforms them to Identifier expressions of the
     * correct JJS type.
     *
     * @param jjsFunction The JJS function.
     * @return The set of instantiated identifiers to pass to the method when calling.
     * @throws RuntimeException If the parameters of the JJS function are not of any JJS type.
     */
    @SuppressWarnings("unchecked")
    private Object[] createMethodParameters(Method jjsFunction) {
        return Arrays
            .stream(jjsFunction.getParameters())
            .map(p -> {
                if (!Any.class.isAssignableFrom(p.getType())) {
                    throw new RuntimeException(
                        String.format(
                            "Wrong parameter type: `%s`, parameter `%s` of method `%s` must be of type `%s`",
                            p.getType().getName(),
                            p.getName(),
                            jjsFunction.getName(),
                            Any.class.getName()
                        )
                    );
                } else {
                    return p;
                }
            })
            .map(p -> Expressions.getIdentifierForType((Class<? extends Any>) p.getType(), p.getName()))
            .toArray();
    }

    /**
     * Creates a proxy which records a single method call. The proxy will translate all calls to other functions
     * into call expressions in the program stack.
     *
     * @param methodToRecord The method to record.
     * @return The proxy instance.
     */
    @SuppressWarnings("unchecked")
    private <T> T createRecordingProxy(Method methodToRecord, Class<T> clazz) {
        var factory = new ProxyFactory();
        factory.setSuperclass(clazz);
        factory.setInterfaces(new Class<?>[]{});

        var jjsFunctions = getJJSFunctions(clazz);

        return (T) Operators.suppressExceptions(() -> factory.create(
            new Class[]{},
            new Object[]{},
            (o, method, proceed, objects) -> {
                if (!jjsFunctions.contains(method)) {
                    // If it's ja Java method (non-JJS), then call it as usual.
                    return proceed.invoke(o, objects);
                } else if (methodToRecord.equals(method)) {
                    // If it's the method which should be recorded by the proxy, call it as usual.
                    return proceed.invoke(o, objects);
                } else {
                    // Create call expression for method call.
                    var callExpression = CallExpression.apply(
                        Identifier.apply(method.getName()),
                        Arrays.stream(objects)
                            .filter(p -> p instanceof Expression)
                            .map(obj -> ((Expression) obj).transpile())
                            .toList()
                    );

                    if (method.getReturnType().equals(void.class)) {
                        // If method has no return type, we assume the call is a imperative call, this we
                        // add the call to the program stack.
                        program.peek().add(ExpressionStatement.apply(callExpression));
                        return null;
                    } else {
                        // If method has a return type, we assume the call is a functional call, this we
                        // return a new instance of the type containing the call expression.
                        if (Bool.class.isAssignableFrom(method.getReturnType())) {
                            return BoolExpression.apply(callExpression);
                        } else if (Lst.class.isAssignableFrom(method.getReturnType())) {
                            return LstExpression.apply(callExpression);
                        } else if (Num.class.isAssignableFrom(method.getReturnType())) {
                            return NumExpression.apply(callExpression);
                        } /* else if (Obj.class.isAssignableFrom(method.getReturnType())) {
                            return ObjExpression.apply(callExpression);
                        } */ else if (Opt.class.isAssignableFrom(method.getReturnType())) {
                            return OptExpression.apply(callExpression);
                        } else if (Str.class.isAssignableFrom(method.getReturnType())) {
                            return StrExpression.apply(callExpression);
                        } else if (Any.class.isAssignableFrom(method.getReturnType())) {
                            return AnyExpression.apply(callExpression);
                        } else {
                            throw new RuntimeException(
                                String.format(
                                    "Wrong return type: `%s`, return value of method `%s` must be of type `%s`",
                                    method.getReturnType().getName(),
                                    method.getName(),
                                    Any.class.getName()
                                )
                            );
                        }
                    }
                }
            }));
    }

}
