package jjs.ast;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "apply")
public class Property {

    Identifier key;

    Expression value;

    boolean method;

    boolean shorthand;

    boolean computed;

    public static Property keyValue(String identifier, Expression expression) {
        return new Property(Identifier.apply(identifier), expression, false, false, false);
    }

    public static Property keyValue(Identifier identifier, Expression expression) {
        return new Property(identifier, expression, false, false, false);
    }

    public static Property computedProperty(String identifier, Expression expression) {
        return new Property(Identifier.apply(identifier), expression, false, false, true);
    }

    public static Property computedProperty(Identifier identifier, Expression expression) {
        return new Property(identifier, expression, false, false, true);
    }

}
