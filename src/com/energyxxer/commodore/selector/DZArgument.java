package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class DZArgument implements SelectorArgument {

    private double value;

    public DZArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dz=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DZArgument clone() {
        return new DZArgument(value);
    }

    @Override
    public String getKey() {
        return "dz";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.Z, ExecutionVariable.DIMENSION);
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.DOUBLE_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new DZArgument(Double.parseDouble(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }
}
