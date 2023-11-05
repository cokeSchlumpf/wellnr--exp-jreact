package com.wellnr.jreact.jreact.html;

import java.util.ArrayList;
import java.util.List;

public class Div extends Element {

    public Div(List<Attribute> attributes, List<Node> children) {
        super("div", List.of(), children);
    }

    public static Div create(Node... children) {
        return new Div(
            List.of(), List.of(children)
        );
    }

    public Div withAttribute(String name, String value) {
        var attributes = new ArrayList<>(this.attributes);
        attributes.add(Attribute.create(name, value));
        return new Div(attributes, children);
    }

}
