package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ForStatement implements Statement {

    VariableDeclaration init;

    Expression condition;

    AssignmentExpression update;

    List<Statement> body;

    public static ForStatement apply(VariableDeclaration init, Expression condition, AssignmentExpression update, List<Statement> body) {
        return new ForStatement(init, condition, update, List.copyOf(body));
    }

}
