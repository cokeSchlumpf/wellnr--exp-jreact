package jjs.ast;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class VariableDeclarator {

    VariableDeclaratorId id;

    Expression init;

}
