package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class XArgument implements SelectorArgument {

    private final double value;

    public XArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "x=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public XArgument clone() {
        return new XArgument(value);
    }

    @Override
    public String getKey() {
        return "x";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.DOUBLE_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new XArgument(Double.parseDouble(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }
}
