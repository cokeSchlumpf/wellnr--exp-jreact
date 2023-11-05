package jjs.lang.references;

import com.wellnr.commons.markup.Either;
import jjs.ast.*;
import jjs.lang.expressions.types.Any;
import jjs.transpiler.TranspilerContext;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Reference<T extends Any> {

    Either<String, Any> name;

    TypeReference<T> typeReference;

    public static <T extends Any> Reference<T> apply(String name, Class<T> typeInterface) {
        return new Reference<>(Either.fromLeft(name), TypeReference.apply(typeInterface));
    }

    public static <T extends Any> Reference<T> apply(String name, TypeReference<T> typeReference) {
        return new Reference<>(Either.fromLeft(name), typeReference);
    }

    public static <T extends Any> Reference<T> apply(Any memberExpression, Class<T> type) {
        return new Reference<>(Either.fromRight(memberExpression), TypeReference.apply(type));
    }

    public static <T extends Any> Reference<T> apply(Any memberExpression, TypeReference<T> typeReference) {
        return new Reference<>(Either.fromRight(memberExpression), typeReference);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        // Create a proxy class of typeInterface
        return (T) Proxy.newProxyInstance(
            jjs.lang.expressions.types.Expression.class.getClassLoader(),
            new Class[]{typeReference.getType()},
            (proxy, method, args) -> {
                if (method.isDefault()) {
                    return InvocationHandler.invokeDefault(proxy, method, args);
                } else if (
                    method.getName().equals("transpile") && method.getReturnType().equals(Expression.class)
                ) {

                } else {
                    throw new RuntimeException("Method not implemented: " + method.getName());
                }
            }
        );
    }

    public void set(T value) {
        TranspilerContext.getBody().add(
            ExpressionStatement.apply(AssignmentExpression.apply(
                getIdentifier(), value.transpile()
            ))
        );
    }

    private Assignable getIdentifier() {
        if (name.isLeft()) {
            return Identifier.apply(name.getLeftForce());
        } else {
            return name.getRightForce().transpile();
        }
    }

}
