package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CallExpression implements Expression {

    Callee callee;

    List<Expression> arguments;

    public static CallExpression apply(Callee callee, List<Expression> arguments) {
        return new CallExpression(callee, List.copyOf(arguments));
    }

    public static CallExpression apply(Callee callee, Expression... arguments) {
        return apply(callee, List.of(arguments));
    }

}
