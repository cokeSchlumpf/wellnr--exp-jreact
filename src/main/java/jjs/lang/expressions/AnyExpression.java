package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Any;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class AnyExpression implements Any {

    Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
