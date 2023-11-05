package jjs.lang.expressions.types;

import jjs.lang.expressions.StrBinaryExpression;

public interface Str extends Any {

    default Str append(Any other) {
        return StrBinaryExpression.apply(this, "+", other);
    }

}
