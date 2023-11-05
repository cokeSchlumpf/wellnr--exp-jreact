package com.wellnr.jreact.jreact.html;

import java.util.ArrayList;
import java.util.List;

public final class H1 extends Element {

    public H1(List<Attribute> attributes, List<Node> children) {
        super("h1", attributes, children);
    }

    public static H1 create(String text) {
        return new H1(List.of(), List.of(Text.create(text)));
    }

    public H1 add(Node ...child) {
        var children = new ArrayList<>(this.children);
        children.addAll(List.of(child));

        return new H1(this.attributes, children);
    }

    public H1 add(String text) {
        return add(Text.create(text));
    }

}
