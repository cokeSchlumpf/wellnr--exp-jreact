package jjs.lang.expressions;

import jjs.ast.BinaryExpression;
import jjs.lang.expressions.types.Any;
import jjs.lang.expressions.types.Bool;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class BoolBinaryExpression implements Bool {

    Any left;

    String operator;

    Any right;

    @Override
    public jjs.ast.Expression transpile() {
        return BinaryExpression.apply(left.transpile(), operator, right.transpile());
    }

}
