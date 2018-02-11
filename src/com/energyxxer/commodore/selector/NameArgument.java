package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class NameArgument implements SelectorArgument {
    private final String name;
    private final boolean negated;

    public NameArgument(String name) {
        if(name.startsWith("!")) {
            this.name = name.substring(1);
            this.negated = true;
        } else {
            this.name = name;
            this.negated = false;
        }
    }

    public NameArgument(String name, boolean negated) {
        this.name = name;
        this.negated = negated;
    }

    public String getName() {
        return name;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "name=" + (negated ? "!" : "") + CommandUtils.escapeIfNecessary(name);
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public NameArgument clone() {
        return new NameArgument(name, negated);
    }

    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher delimited = CommandUtils.DELIMITED_STRING_REGEX.matcher(str);
        if(delimited.lookingAt()) {
            String group = delimited.group();
            return new SelectorArgumentParseResult(group, new NameArgument(CommandUtils.unescape(group)));
        }
        Matcher simple = CommandUtils.SELECTOR_STRING_REGEX.matcher(str);
        if(simple.lookingAt()) {
            String group = simple.group();
            return new SelectorArgumentParseResult(group, new NameArgument(group));
        }
        throw new IllegalArgumentException("Expected string at: " + str);
    }
}
