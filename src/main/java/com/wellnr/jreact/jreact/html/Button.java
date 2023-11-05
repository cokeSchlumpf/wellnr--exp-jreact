package com.wellnr.jreact.jreact.html;

import java.util.List;
import java.util.function.Supplier;

public class Button extends Element {

    public Button(List<Attribute> attributes, List<Node> children) {
        super("button", attributes, children);
    }

    public static Button create(Text text, Attribute ...attributes) {
        return new Button(List.of(attributes), List.of(text));
    }

    public static Button create(String text, Attribute ...attributes) {
        return create(Text.create(text), attributes);
    }

    public Button onChange(Runnable action) {
        // Do sometging.
        return this;
    }

}
