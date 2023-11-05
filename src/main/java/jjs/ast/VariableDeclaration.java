package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VariableDeclaration implements Statement {

    VariableDeclarationKind kind;

    List<VariableDeclarator> declarations;

    public static VariableDeclaration apply(VariableDeclarationKind kind, List<VariableDeclarator> declarations) {
        return new VariableDeclaration(kind, List.copyOf(declarations));
    }

    public static VariableDeclaration apply(VariableDeclarationKind kind, VariableDeclarator... declarations) {
        return apply(kind, List.of(declarations));
    }

}
