package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Any;
import jjs.lang.expressions.types.Lst;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class LstExpression<T extends Any> implements Lst<T> {

    Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
