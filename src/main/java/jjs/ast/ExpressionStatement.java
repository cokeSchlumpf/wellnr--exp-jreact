package jjs.ast;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "apply")
public class ExpressionStatement implements Statement {

    Expression expression;

}
