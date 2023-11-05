package jjs.lang.expressions;

import jjs.ast.BinaryExpression;
import jjs.lang.expressions.types.Num;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class NumBinaryExpression implements Num {

    Num left;

    String operator;

    Num right;

    @Override
    public jjs.ast.Expression transpile() {
        return BinaryExpression.apply(left.transpile(), operator, right.transpile());
    }

}
