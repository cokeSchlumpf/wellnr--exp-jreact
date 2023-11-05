package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Num;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class NumExpression implements Num {

    Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
