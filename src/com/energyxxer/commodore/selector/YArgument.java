
package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class YArgument implements SelectorArgument {

    private double value;

    public YArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "y=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public YArgument clone() {
        return new YArgument(value);
    }

    @Override
    public String getKey() {
        return "y";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.DOUBLE_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new YArgument(Double.parseDouble(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }
}
