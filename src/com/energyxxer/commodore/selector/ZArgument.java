package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class ZArgument implements SelectorArgument {

    private double value;

    public ZArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "z=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public ZArgument clone() {
        return new ZArgument(value);
    }

    @Override
    public String getKey() {
        return "z";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.DOUBLE_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new ZArgument(Double.parseDouble(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }
}
