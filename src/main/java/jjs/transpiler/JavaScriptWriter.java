package jjs.transpiler;

import jjs.ast.*;

import java.util.List;
import java.util.stream.Collectors;

public class JavaScriptWriter {

    private final int indent;

    public JavaScriptWriter(int indent) {
        this.indent = indent;
    }

    public JavaScriptWriter() {
        this(3);
    }

    public String writeAsString(Program program) {
        return writeAsString(program.getBody());
    }

    public String writeAsString(List<Statement> body) {
        return body.stream().map(this::writeAsString).collect(Collectors.joining("\n"));
    }

    private String writeAsString(Statement statement) {
        if (statement instanceof VariableDeclaration vd) {
            return writeAsString(vd);
        } else if (statement instanceof ExpressionStatement expr) {
            return writeAsString(expr);
        } else if (statement instanceof FunctionDeclaration func) {
            return writeAsString(func);
        } else if (statement instanceof ReturnStatement ret) {
            return writeAsString(ret);
        }  else if (statement instanceof ForStatement ret) {
            return writeAsString(ret);
        } else if (statement instanceof NoStatememt) {
            return "";
        } else {
            throw new RuntimeException("Unknown statement type: " + statement.getClass().getName());
        }
    }

    private String writeAsString(ForStatement stmt) {
        var init = writeAsString(stmt.getInit());
        var condition = writeAsString(stmt.getCondition());
        var update = writeAsString(stmt.getUpdate());
        var body = writeAsString(stmt.getBody());

        init = init.replace(";", "");
        body = body.indent(indent);
        return String.format("for (%s; %s; %s) {\n%s}", init, condition, update, body);
    }

    private String writeAsString(ReturnStatement ret) {
        return String.format("return %s;", writeAsString(ret.getArgument()));
    }

    private String writeAsString(FunctionDeclaration func) {
        var id = func.getId().getName();
        var params = func.getParams().stream().map(this::writeAsString).collect(Collectors.joining(", "));
        var body = func.getBody().stream()
            .map(this::writeAsString)
            .collect(Collectors.joining("\n"))
            .indent(indent);

        return String.format("function %s(%s) {\n%s}", id, params, body);
    }

    private String writeAsString(ExpressionStatement expr) {
        return writeAsString(expr.getExpression()) + ";";
    }

    private String writeAsString(VariableDeclaration vd) {
        var kind = vd.getKind().toString().toLowerCase();
        var declarations = vd.getDeclarations().stream().map(this::writeAsString).collect(Collectors.joining(", "));

        return String.format("%s %s;", kind, declarations);
    }

    private String writeAsString(VariableDeclarator vd) {
        var id = writeAsString(vd.getId());
        var init = writeAsString(vd.getInit());

        return id + " = " + init;
    }

    private String writeAsString(VariableDeclaratorId vid) {
        if (vid instanceof Identifier id) {
            return id.getName();
        } else { // if (vid instanceof ObjectPattern obj) {
            return "{}";  // TODO
        }
    }

    private String writeAsString(Expression expr) {
        if (expr instanceof Literal l) {
            return writeAsString(l);
        } else if (expr instanceof Identifier id) {
            return id.getName();
        } else if (expr instanceof CallExpression call) {
            return writeAsString(call);
        } else if (expr instanceof BinaryExpression bin) {
            return writeAsString(bin);
        } else if (expr instanceof AssignmentExpression ass) {
            return  writeAsString(ass);
        } else {
            throw new RuntimeException("Unknown expression type: " + expr.getClass().getName());
        }
    }

    private String writeAsString(AssignmentExpression expr) {
        var left = writeAsString(expr.getId());
        var right = writeAsString(expr.getInit());

        return left + " = " + right;
    }

    private String writeAsString(BinaryExpression expr) {
        var left = writeAsString(expr.getLeft());
        var right = writeAsString(expr.getRight());

        // TODO: Braces?
        return left + " " + expr.getOperator() + " " + right;
    }

    private String writeAsString(CallExpression call) {
        var callee = writeAsString(call.getCallee());
        var args = call.getArguments().stream().map(this::writeAsString).collect(Collectors.joining(", "));

        return callee + "(" + args + ")";
    }

    private String writeAsString(Callee callee) {
        if (callee instanceof Identifier id) {
            return id.getName();
        } else {
            throw new RuntimeException("Unknown callee type: " + callee.getClass().getName());
        }
    }

    private String writeAsString(Literal literal) {
        if (literal.isQuoted()) {
            return "\"" + literal.getValue() + "\"";
        } else {
            return literal.getValue().toString();
        }
    }

}
