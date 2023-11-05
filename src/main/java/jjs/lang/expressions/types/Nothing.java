package jjs.lang.expressions.types;

import jjs.ast.Expression;
import jjs.ast.Identifier;

public final class Nothing implements Any {

    private static final Nothing instance = new Nothing();

    private Nothing() {

    }

    public static Nothing getInstance() {
        return instance;
    }

    @Override
    public Expression transpile() {
        return Identifier.apply("undefined");
    }

}
