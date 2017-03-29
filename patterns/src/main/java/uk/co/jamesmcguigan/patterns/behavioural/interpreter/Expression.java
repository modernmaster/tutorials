package uk.co.jamesmcguigan.patterns.behavioural.interpreter;

import java.util.Map;

public interface Expression {
    int interpret(Map<String,Expression> variables);
}

