package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Program {

    List<Statement> body;

    public static Program apply(List<Statement> body) {
        return new Program(List.copyOf(body));
    }

    public static Program apply(Statement ...body) {
        return apply(List.of(body));
    }

    public Program withStatement(Statement ...body) {
        var allStatements = new ArrayList<Statement>();
        allStatements.addAll(this.body);
        allStatements.addAll(List.of(body));
        return Program.apply(allStatements);
    }

}
