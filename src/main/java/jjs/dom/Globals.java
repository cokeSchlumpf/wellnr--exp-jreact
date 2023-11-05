package jjs.dom;

import com.wellnr.commons.functions.Function1;
import com.wellnr.commons.functions.Procedure1;
import jjs.ast.*;
import jjs.lang.expressions.types.*;
import jjs.lang.literals.BooleanLiteral;
import jjs.lang.literals.NumLiteral;
import jjs.lang.literals.StringLiteral;
import jjs.lang.references.Reference;
import jjs.transpiler.TranspilerContext;

public class Globals {

    public static Console console = new Console();

    /**
     * Displays an alert box with a message and an OK button on the client.
     *
     * @param message The message to be shown.
     */
    public static void alert(Any message) {
        TranspilerContext.getBody().add(ExpressionStatement.apply(
            CallExpression.apply(
                Identifier.apply("alert"),
                message.transpile()
            )
        ));
    }

    /**
     * Displays an alert box with a message and an OK button.
     *
     * @param message The message to be shown.
     */
    public static void alert(String message) {
        alert(StringLiteral.apply(message));
    }

    /**
     * Declares a client-side variable which can be used for client-side computations.
     *
     * @param initialValue The initial value.
     * @param name         The client-side variable name.
     * @param <T>          The type of the variable.
     * @return A variable reference which can be used for client-side logic.
     */
    @SuppressWarnings({"unchecked", "unused"})
    public static <T extends Any> Reference<T> declare(T initialValue, String name) {
        TranspilerContext.getBody().add(
            VariableDeclaration.apply(
                VariableDeclarationKind.VAR,
                VariableDeclarator.apply(Identifier.apply(name), initialValue.transpile())
            )
        );

        var typeClass = (Class<T>) Expressions.getTypeFromExpression(initialValue);
        return Reference.apply(name, typeClass);
    }

    /**
     * Declares a client-side variable which can be used for client-side computations. A random name will be generated.
     *
     * @param initialValue The initial value.
     * @param <T>          The type of the variable.
     * @return A variable reference which can be used for client-side logic.
     */
    public static <T extends Any> Reference<T> declare(T initialValue) {
        return declare(initialValue, "var_" + TranspilerContext.getAndUpdateVariableCounter());
    }

    /**
     * The for statement defines a code block that is executed as long as a condition is true.
     *
     * @param initialValue The initial value of the loop variable.
     * @param cond         A condition which is checked for each iteration.
     * @param updateFunc   A function which is called for each iteration.
     * @param body         The body of the loop.
     * @param <T>          The type of the variable.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Any> void forLoop(
        T initialValue,
        Function1<T, Bool> cond,
        Function1<T, T> updateFunc,
        Procedure1<T> body) {

        var loopParamName = "var_" + TranspilerContext.getAndUpdateVariableCounter();
        var typeClass = (Class<T>) Expressions.getTypeFromExpression(initialValue);
        var loopParam = Reference.apply(loopParamName, typeClass);
        var condExpr = cond.get(loopParam.get());
        var updateExpr = updateFunc.get(loopParam.get());

        var bodyContent = TranspilerContext.getInstance().recordBody(() -> {
            body.run(loopParam.get());
            return null;
        }, false);

        TranspilerContext.getBody().add(ForStatement.apply(
            VariableDeclaration.apply(
                VariableDeclarationKind.VAR,
                VariableDeclarator.apply(Identifier.apply(loopParamName), initialValue.transpile())
            ),
            condExpr.transpile(),
            AssignmentExpression.apply(Identifier.apply(loopParamName), updateExpr.transpile()),
            bodyContent
        ));
    }

    /**
     * Define a client-side value.
     *
     * @param v The value.
     * @return A static client-side value.
     */
    public static Num value(int v) {
        return NumLiteral.apply(v);
    }

    /**
     * Define a client-side value.
     *
     * @param v The value.
     * @return A static client-side value.
     */
    public static Num value(double v) {
        return NumLiteral.apply(v);
    }

    /**
     * Define a client-side value.
     *
     * @param v The value.
     * @return A static client-side value.
     */
    public static Str value(String v) {
        return StringLiteral.apply(v);
    }

    /**
     * Define a client-side value.
     *
     * @param v The value.
     * @return A static client-side value.
     */
    public static Bool value(boolean v) {
        return BooleanLiteral.apply(v);
    }

}
