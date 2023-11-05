package jjs;

import com.wellnr.jreact.jreact.html.Node;
import com.wellnr.jreact.jreact.html.Text;
import jjs.lang.expressions.types.Num;
import jjs.lang.expressions.types.Str;
import jjs.lang.references.Reference;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.wellnr.jreact.jreact.html.L.*;
import static jjs.dom.Globals.*;

public class JJSSample {

    private final String foo;

    private Reference<Str> someValue;

    public JJSSample() {
        this("foo");
    }

    public JJSSample(String foo) {
        this.foo = foo;
    }

    @Before
    public void declarations() {
        someValue = declare(value("Hallo"));
    }

    public Num get_value() {
        return value(42);
    }

    public void test(Str value) {
        var a = declare(value);

        forLoop(
            get_value(),
            i -> i.gt(0),
            i -> i.minus(1),
            i -> {
                alert(value("Hallo ").append(value));
                alert(i);
            }
        );

        alert(a.get());
        console.log(foo);
        alert(someValue.get());
    }

    public static List<Node> f(String str) {
        var regex = Pattern.compile("\\{\\{[\\s]*[a-zA-Z_0-9]+[\\s]*}}");
        var matcher = regex.matcher(str);

        int position = 0;
        List<Node> nodes = new ArrayList<>();

        while (matcher.find()) {
            // Get the matching string
            var result = matcher.toMatchResult();
            nodes.add(Text.create(str.substring(position, result.start())));
            nodes.add(Text.create(str.substring(result.start(), result.end()).replace("{", "").replace("}", "").trim()));
            position = result.end();
        }

        nodes.add(Text.create(str.substring(position)));
        return nodes;
    }

    public Node Greeting(Str name) {
        return h1(f("Hello {{ name }}. Welcome!"));
    }

    public void main() {
        System.out.println(Greeting(value("World")).toHtml());

        var a = declare(value("Hello"));
        this.test(a.get());
    }

}
