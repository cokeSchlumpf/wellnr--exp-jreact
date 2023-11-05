package jjs.dom.react;

import com.wellnr.jreact.jreact.html.Node;
import jjs.lang.expressions.types.Obj;

import java.util.List;

public class React {

    public static ReactElement createElement(String type, Obj props, Node... children) {
        return ReactElement.apply(type, props, List.of(children));
    }

}
