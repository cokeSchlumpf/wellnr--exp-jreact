package com.wellnr.jreact.jreact.html;

import java.util.List;

public final class Title extends Element {

    public Title(List<Attribute> attributes, List<Node> children) {
        super("title", attributes, children);
    }

    public static Title create(String text) {
        return new Title(List.of(), List.of(Text.create(text)));
    }

}
