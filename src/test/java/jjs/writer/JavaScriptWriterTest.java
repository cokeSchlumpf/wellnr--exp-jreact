package jjs.writer;

import jjs.JJSSample;
import jjs.ast.*;
import jjs.transpiler.JavaScriptWriter;
import jjs.transpiler.Transpiler;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class JavaScriptWriterTest {

    @Test
    public void variableDeclaration() {
        var ast = Program.apply(
            VariableDeclaration.apply(
                VariableDeclarationKind.VAR,
                VariableDeclarator.apply(Identifier.apply("foo"), Literal.apply("bar")),
                VariableDeclarator.apply(Identifier.apply("lorem"), Literal.apply("ipsum"))
            ),

            ExpressionStatement.apply(CallExpression.apply(
                Identifier.apply("alert"),
                Identifier.apply("foo")
            ))
        );

        var writer = new JavaScriptWriter();
        var js = writer.writeAsString(ast);

        System.out.println(js);
    }

    @Test
    public void test() {
        var sample = new JJSSample("Lorem Ipsum");
        var js = Transpiler.writeJavaScriptAsString(sample);

        System.out.println("--");
        System.out.println(js);
    }

    @Test
    public void testGenerics() {
        var l = new ArrayList<String>();
        l.add("foo");

        l.getClass().getGenericSuperclass();
    }

}