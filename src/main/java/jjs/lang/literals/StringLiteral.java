package jjs.lang.literals;

import jjs.ast.Expression;
import jjs.ast.Literal;
import jjs.lang.expressions.types.Str;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class StringLiteral implements Str {

    String value;

    @Override
    public Expression transpile() {
        return Literal.apply(value);
    }
}
