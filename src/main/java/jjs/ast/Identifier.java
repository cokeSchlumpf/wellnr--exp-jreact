package jjs.ast;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class Identifier implements Expression, VariableDeclaratorId, Callee, Assignable, MemberObject {

    String name;

}
