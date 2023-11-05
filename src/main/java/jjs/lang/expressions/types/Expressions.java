package jjs.lang.expressions.types;

import jjs.ast.Identifier;

public final class Expressions {

    private Expressions() {

    }

    @SuppressWarnings("unchecked")
    public static <T extends Any> T getIdentifierForType(Class<? extends Expression> expressionType, String name) {
        if (expressionType.equals(Any.class)) {
            return (T) (Any) () -> Identifier.apply(name);
        } else if (Bool.class.isAssignableFrom(expressionType)) {
            return (T) (Bool) () -> Identifier.apply(name);
        } else if (Lst.class.isAssignableFrom(expressionType)) {
            return (T) (Lst<?>) () -> Identifier.apply(name);
        } else if (Num.class.isAssignableFrom(expressionType)) {
            return (T) (Num) () -> Identifier.apply(name);
        } else if (Opt.class.isAssignableFrom(expressionType)) {
            return (T) (Opt<?>) () -> Identifier.apply(name);
        } else if (Str.class.isAssignableFrom(expressionType)) {
            return (T) (Str) () -> Identifier.apply(name);
        } else {
            throw new IllegalArgumentException("No valid expression supertype found.");
        }
    }

    public static Class<? extends Any> getTypeFromExpression(Expression expression) {
        if (expression instanceof Bool) {
            return Bool.class;
        } else if (expression instanceof Lst) {
            return Lst.class;
        } else if (expression instanceof Num) {
            return Num.class;
        } else if (expression instanceof Obj) {
            return Obj.class;
        } else if (expression instanceof Opt) {
            return Opt.class;
        } else if (expression instanceof Str) {
            return Str.class;
        } else if (expression instanceof Any) {
            return Any.class;
        } else {
            throw new IllegalArgumentException("No valid expression supertype found.");
        }
    }

    public static Class<? extends Any> getTypeFromExpression(Class<? extends Expression> expression) {
        if (Bool.class.isAssignableFrom(expression)) {
            return Bool.class;
        } else if (Lst.class.isAssignableFrom(expression)) {
            return Lst.class;
        } else if (Num.class.isAssignableFrom(expression)) {
            return Num.class;
        } else if (Obj.class.isAssignableFrom(expression)) {
            return Obj.class;
        } else if (Opt.class.isAssignableFrom(expression)) {
            return Opt.class;
        } else if (Str.class.isAssignableFrom(expression)) {
            return Str.class;
        } else if (Any.class.isAssignableFrom(expression)) {
            return Any.class;
        } else {
            throw new IllegalArgumentException("No valid expression supertype found.");
        }
    }

}
