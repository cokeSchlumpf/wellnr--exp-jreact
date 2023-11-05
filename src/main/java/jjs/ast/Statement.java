package jjs.ast;

sealed public interface Statement permits ExpressionStatement, ForStatement, FunctionDeclaration, NoStatememt, ReturnStatement, VariableDeclaration {
}
