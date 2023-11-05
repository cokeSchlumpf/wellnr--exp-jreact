package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Any;
import jjs.lang.expressions.types.Opt;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class OptExpression<T extends Any> implements Opt<T> {

    Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
