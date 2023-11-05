package jjs.dom;

import jjs.ast.CallExpression;
import jjs.ast.ExpressionStatement;
import jjs.ast.Identifier;
import jjs.lang.expressions.types.Any;
import jjs.lang.literals.StringLiteral;
import jjs.transpiler.TranspilerContext;

import java.util.Arrays;

public final class Console {

    public void log(Any... messages) {
        for (Any message : messages) {
            TranspilerContext.getBody().add(ExpressionStatement.apply(
                CallExpression.apply(Identifier.apply("console.log"), message.transpile())
            ));
        }
    }

    public void log(String... messages) {
        var values = Arrays.stream(messages).map(StringLiteral::apply).map(obj -> (Any) obj).toList();
        log(values.toArray(new Any[0]));
    }

}
