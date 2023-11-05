package jjs.lang.literals;

import jjs.ast.Expression;
import jjs.ast.Literal;
import jjs.lang.expressions.types.Bool;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class BooleanLiteral implements Bool {

    boolean value;

    @Override
    public Expression transpile() {
        return Literal.apply(value);
    }

}
