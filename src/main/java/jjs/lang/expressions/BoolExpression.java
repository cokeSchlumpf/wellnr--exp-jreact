package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Bool;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class BoolExpression implements Bool {

    jjs.ast.Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
