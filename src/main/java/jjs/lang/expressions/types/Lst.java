package jjs.lang.expressions.types;

public interface Lst<T extends Expression> extends Any {

    /*
    default Lst<T> append(T item) {
        return AppendExpression.apply(this, item);
    }


    default Opt<T> find(Class<T> itemType, Function1<T, Bool> callbackFn) {
        return FindExpression.apply(this, itemType, (element, index, array) -> callbackFn.invoke(element));
    }

    default Opt<T> find(Class<T> itemType, Function2<T, Num, Bool> callbackFn) {
        return FindExpression.apply(this, itemType, (element, index, array) -> callbackFn.invoke(element, index));
    }

    default Opt<T> find(Class<T> itemType, Function3<T, Num, Lst<T>, Bool> callbackFn) {
        return FindExpression.apply(this, itemType, callbackFn);
    }
    */

}
