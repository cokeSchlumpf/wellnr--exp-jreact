package jjs.lang.expressions;

import jjs.ast.Expression;
import jjs.lang.expressions.types.Str;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class StrExpression implements Str {

    Expression expression;

    @Override
    public Expression transpile() {
        return expression;
    }

}
