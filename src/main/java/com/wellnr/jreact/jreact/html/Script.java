package com.wellnr.jreact.jreact.html;

import java.util.List;

public class Script extends Element {

    public Script(List<Attribute> attributes, List<Node> children) {
        super("script", attributes, children);
    }

    public static Script src(String src) {
        return new Script(
            List.of(Attribute.create("src", src)),
            List.of()
        );
    }

    public static Script js(String jsCode) {
        return new Script(
            List.of(Attribute.create("type", "text/javascript")),
            List.of(Text.create(jsCode))
        );
    }

}
