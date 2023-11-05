package com.wellnr.jreact.jreact.html;

import jjs.lang.expressions.types.Expression;
import jjs.lang.literals.StringLiteral;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(staticName = "create")
public class Text extends Node {

    String text;

    @Override
    public String toHtml() {
        return text;
    }

    @Override
    public Expression toExpression() {
        return StringLiteral.apply(text);
    }

}
