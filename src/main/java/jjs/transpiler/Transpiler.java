package jjs.transpiler;

import com.wellnr.commons.Operators;
import com.wellnr.commons.ReflectionOperators;
import jjs.Before;
import jjs.ast.NoStatememt;
import jjs.ast.Program;
import jjs.ast.Statement;

import java.util.ArrayList;

public class Transpiler {

    public Program transpile(Object obj) {
        var context = TranspilerContext.getInstance();
        var moduleStatements = new ArrayList<Statement>();

        /*
         * Transpile methods which are annotated with @Before
         */
        for (var jjsFunction : ReflectionOperators.getMethodsWithAnnotation(obj.getClass(), Before.class)) {
            if (!jjsFunction.getReturnType().equals(void.class) || jjsFunction.getParameters().length > 0) {
                continue;
            }

            var func = context.recordMethod(jjsFunction, obj);
            moduleStatements.addAll(func.getBody());
            moduleStatements.add(NoStatememt.apply());
        }

        /*
         * Define methods in JS module.
         */
        for (var jjsFunction : context.getJJSFunctions(obj.getClass())) {
            moduleStatements.add(context.recordMethod(jjsFunction, obj));
            moduleStatements.add(NoStatememt.apply());
        }

        /*
         * Define main code after all function declarations.
         */
        var mainMethod = Operators.ignoreExceptionsToOptional(() -> obj.getClass().getMethod("main"));
        if (mainMethod.isPresent() && mainMethod.get().getReturnType().equals(void.class)) {
            var func = context.recordMethod(mainMethod.get(), obj);
            moduleStatements.addAll(func.getBody());
        }

        return Program.apply(moduleStatements);
    }

    public static String writeJavaScriptAsString(Object obj) {
        var writer = new JavaScriptWriter();
        var transpiler = new Transpiler();

        return writer.writeAsString(transpiler.transpile(obj));
    }

}
