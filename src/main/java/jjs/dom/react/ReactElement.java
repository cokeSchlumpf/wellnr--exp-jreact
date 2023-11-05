package jjs.dom.react;

import com.wellnr.jreact.jreact.html.Element;
import com.wellnr.jreact.jreact.html.Node;
import jjs.ast.*;
import jjs.lang.expressions.types.Expression;
import jjs.lang.expressions.types.Obj;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ReactElement extends Element implements Expression {

    String type;

    Obj props;

    List<Node> children;

    public ReactElement(String type, Obj props, List<Node> children) {
        super(type, List.of(), children);
    }

    public static ReactElement apply(String type, Obj props, List<Node> children) {
        return new ReactElement(type, props, List.copyOf(children));
    }

    @Override
    public String toHtml() {
        return "<" + type +">" ;
    }

    @Override
    public Expression toExpression() {
        return this;
    }

    @Override
    public jjs.ast.Expression transpile() {
        var parameters = new ArrayList<jjs.ast.Expression>();
        parameters.add(Literal.apply(type));
        parameters.add(ObjectExpression.apply(List.of()));

        return CallExpression.apply(
            MemberExpression.apply(Identifier.apply("React"), Identifier.apply("createElement")),
            parameters
        );
    }

}
