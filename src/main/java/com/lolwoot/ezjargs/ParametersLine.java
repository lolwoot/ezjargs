package com.lolwoot.ezjargs;

import com.lolwoot.ezjargs.options.AbstractOption;

import java.util.Stack;

public class ParametersLine {

    static final String PARAMETERS_NAME = "parameters";

    private final Stack<String> line;

    public ParametersLine(String[] args) {
        this.line = new Stack<>();
        for (int i = args.length -1; i >= 0; --i) {
            this.line.push(args[i]);
        }
    }

    public boolean isEmpty() {
        return line.empty();
    }

    public String getToken() {
        /*
            2 cases:
                First:  if getting option name "-s".
                Second: if getting simple word "value" then it cant be option value
                        because all optValues processed by Processor class, so it parameter value.
                        For parameter values return PARAMETERS_NAME constant

         */
        if (!isOptionName(line.peek())) return PARAMETERS_NAME;

        return line.pop();
    }

    private boolean isOptionName(String takenElement) {
        return takenElement.startsWith("-");
    }

    public void processOption(AbstractOption option) {
        option.process(this);
    }

    public String next() {
        return line.pop();
    }

    public boolean hasNext() {
        return !line.empty();
    }

    public boolean isNextOption() {
        return isOptionName(line.peek());
    }
}
