package jjs.lang.literals;

import jjs.ast.Expression;
import jjs.ast.Literal;
import jjs.lang.expressions.types.Num;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply", access = AccessLevel.PRIVATE)
public class NumLiteral implements Num {

    Object value;

    public static Num apply(int value) {
        return apply((Integer) value);
    }

    public static Num apply(double value) {
        return apply((Double) value);
    }

    @Override
    public Expression transpile() {
        if (value instanceof Integer i) {
            return Literal.apply(i);
        } else if (value instanceof Double d) {
            return Literal.apply(d);
        }

        throw new IllegalStateException("Unknown type: " + value.getClass().getName());
    }
}
