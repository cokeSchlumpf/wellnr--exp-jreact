package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectExpression implements Expression, MemberObject {

    List<Property> properties;

    public static ObjectExpression apply(List<Property> properties) {
        return new ObjectExpression(List.copyOf(properties));
    }

}
