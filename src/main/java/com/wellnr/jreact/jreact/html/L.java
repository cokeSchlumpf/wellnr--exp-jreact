package com.wellnr.jreact.jreact.html;

import jjs.lang.expressions.types.Expression;

import java.util.List;

public class L {

    public static Div div(Node... children) {
        return Div.create(children);
    }

    public static H1 h1(String text) {
        return H1.create(text);
    }

    public static H1 h1(Node... children) {
        return new H1(List.of(), List.of(children));
    }

    public static H1 h1(List<Node> children) { return new H1(List.of(), List.copyOf(children)); }

    public static ExpressionElement expr(Expression expression) {
        return ExpressionElement.create(expression);
    }

    public static Text text(String text) {
        return Text.create(text);
    }

}
