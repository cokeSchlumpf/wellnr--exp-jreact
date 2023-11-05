package com.wellnr.jreact.jreact.html;

import java.util.List;

public class Head extends Element {

    public Head(List<Node> children) {
        super("head", List.of(), children);
    }

    public static Head create(Node... children) {
        return new Head(List.of(children));
    }

}
