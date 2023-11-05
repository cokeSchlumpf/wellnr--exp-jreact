package jjs.lang.expressions.types;

import jjs.lang.literals.StringLiteral;
import jjs.lang.references.TypeReference;
import jjs.lang.references.Reference;

public interface Obj extends Any {

    default Reference<Lst<Str>> keys() {
        return  Reference.apply("foo", TypeReference.apply(Lst.class, Str.class));
    }

    <T extends Any> T get(Str key);

    default <T extends Any> T get(String key) {
        return get(StringLiteral.apply(key));
    }

    <T extends Any> T get(Str key, T defaultValue);

    default <T extends Any> T get(String key, T defaultValue) {
        return get(StringLiteral.apply(key), defaultValue);
    }

    void set(Str key, Any value);

    default void set(String key, Any value) {
        set(StringLiteral.apply(key), value);
    }

}
