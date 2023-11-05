package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply", access = AccessLevel.PRIVATE)
public class Literal implements Expression {

    Object value;

    boolean quoted;

    public static Literal apply(String value) {
        return new Literal(value, true);
    }

    public static Literal apply(int value) {
        return new Literal(value, false);
    }

    public static Literal apply(double value) {
        return new Literal(value, false);
    }

    public static Literal apply(boolean value) {
        return new Literal(value, false);
    }

}
