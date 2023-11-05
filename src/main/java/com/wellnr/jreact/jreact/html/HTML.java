package com.wellnr.jreact.jreact.html;

import java.util.List;

public class HTML extends Element {

    public HTML(List<Attribute> attributes, List<Node> children) {
        super("html", attributes, children);
    }

    public static HTML create(List<Node> children) {
        return new HTML(List.of(), children);
    }

    public static HTML create(Node ...children) {
        return create(List.of(children));
    }

}
