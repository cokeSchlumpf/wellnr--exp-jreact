package jjs.ast;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class MemberExpression implements Expression, Callee, Assignable {

    MemberObject object;

    Identifier property;

}
