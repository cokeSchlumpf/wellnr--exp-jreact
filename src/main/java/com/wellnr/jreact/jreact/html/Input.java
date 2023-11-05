package com.wellnr.jreact.jreact.html;

import java.util.ArrayList;
import java.util.List;

public class Input extends Element {

    public Input(List<Attribute> attributes, List<Node> children) {
        super("input", attributes, children);
    }

    public static Input create(String type, List<Attribute> attributes) {
        var allAttributes = new ArrayList<Attribute>();
        allAttributes.add(Attribute.create("type", type));
        allAttributes.addAll(attributes);

        return new Input(allAttributes, List.of());
    }

    public static Input create(String type, Attribute ...attributes) {
        return create(type, List.of(attributes));
    }

}
