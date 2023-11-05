package com.wellnr.jreact.jreact.html;

import java.util.List;

public class Body extends Element {

    public Body(List<Node> children) {
        super("body", List.of(), children);
    }

    public static Body create(Node... children) {
        return new Body(List.of(children));
    }

}
