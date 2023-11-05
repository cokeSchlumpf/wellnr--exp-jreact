package jjs.lang.expressions.types;

public interface Opt<T extends Expression> extends Any {

    /*
    default Bool isPresent() {
        return IsPresentExpression.apply(this);
    }

    default Opt<T> orElse(Bool condition, T expression) {
        return OtherwiseExpression.apply(this, condition, expression);
    }

    default T orElse(T expression) {
        return OrElseExpression.apply(this, expression);
    }
     */

}
