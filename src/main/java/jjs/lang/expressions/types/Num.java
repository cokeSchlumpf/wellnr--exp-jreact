package jjs.lang.expressions.types;


import jjs.lang.expressions.BoolBinaryExpression;
import jjs.lang.expressions.NumBinaryExpression;
import jjs.lang.literals.NumLiteral;

public interface Num extends Any {

    default Bool equals(Num other) {
        return BoolBinaryExpression.apply(this, "==", other);
    }

    default Num plus(Num other) {
        return NumBinaryExpression.apply(this, "+", other);
    }

    default Num minus(Num other) {
        return NumBinaryExpression.apply(this, "-", other);
    }

    default Num minus(int other) {
        return this.minus(NumLiteral.apply(other));
    }

    default Bool lowerThan(Num other) {
        return BoolBinaryExpression.apply(this, "<", other);
    }

    default Bool lt(Num other) {
        return lowerThan(other);
    }

    default Bool lowerOrEq(Num other) {
        return BoolBinaryExpression.apply(this, "<=", other);
    }

    default Bool leq(Num other) {
        return lowerOrEq(other);
    }

    default Bool greaterThan(Num other) {
        return BoolBinaryExpression.apply(this, ">", other);
    }

    default Bool greaterOrEq(Num other) {
        return BoolBinaryExpression.apply(this, ">=", other);
    }

    default Bool gt(Num other) {
        return this.greaterThan(other);
    }

    default Bool gt(int other) {
        return this.gt(NumLiteral.apply(other));
    }

    default Bool geq(Num other) {
        return this.greaterOrEq(other);
    }

}
