package uk.co.jamesmcguigan.patterns.behavioural.interpreter;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {

    @Test
    public void testSentenceIsInterpreted() {
        String expression = "w x z - +";
        Evaluator sentence = new Evaluator(expression);
        Map<String,Expression> variables = new HashMap<String,Expression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("z", new Number(42));
        int result = sentence.interpret(variables);
        System.out.println(result);
    }
}
