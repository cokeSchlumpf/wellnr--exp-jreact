package com.wellnr.jreact.jreact.html;

import jjs.dom.react.ReactElement;
import jjs.lang.expressions.types.Expression;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class Element extends Node {

    String name;

    List<Attribute> attributes;

    List<Node> children;

    @Override
    public String toHtml() {
        var children = this
            .children
            .stream()
            .map(Node::toHtml)
            .collect(Collectors.joining(""));

        var attributes = this
            .attributes
            .stream()
            .map(attr -> String.format(" %s=\"%s\"", attr.getName(), attr.getValue()))
            .collect(Collectors.joining());

        return String.format(
            "<%s%s>%s</%s>",
            name,
            attributes,
            children,
            name
        );
    }

    @Override
    public Expression toExpression() {
        return ReactElement.apply(name, null, children);
    }

}
