package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class DYArgument implements SelectorArgument {

    private double value;

    public DYArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dy=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DYArgument clone() {
        return new DYArgument(value);
    }

    @Override
    public String getKey() {
        return "dy";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.Y, ExecutionVariable.DIMENSION);
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.DOUBLE_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new DYArgument(Double.parseDouble(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }
}
