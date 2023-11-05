package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FunctionDeclaration implements Statement {

    Identifier id;

    List<VariableDeclaratorId> params;

    List<Statement> body;

    public static FunctionDeclaration apply(Identifier id, List<VariableDeclaratorId> params, List<Statement> body) {
        return new FunctionDeclaration(id, List.copyOf(params), List.copyOf(body));
    }

}
