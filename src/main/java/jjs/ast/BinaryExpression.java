package jjs.ast;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(staticName = "apply")
public class BinaryExpression implements Expression {

    Expression left;

    String operator;

    Expression right;

}
