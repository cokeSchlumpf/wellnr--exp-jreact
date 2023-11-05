package com.wellnr.jreact.jreact.html;

import jjs.lang.expressions.types.Expression;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(staticName = "create")
public class ExpressionElement extends Node {

    Expression expression;

    @Override
    public String toHtml() {
        return "{{ " + expression.transpile() + " }}";
    }

    @Override
    public Expression toExpression() {
        return expression;
    }

}
