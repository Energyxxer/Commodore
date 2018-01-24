package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class LimitArgument implements SelectorArgument {
    private int limit;

    public LimitArgument(int limit) {
        if(limit <= 0) throw new IllegalArgumentException("Limit must be greater than zero: " + limit);
        this.limit = limit;
    }

    @Override
    public String getArgumentString() {
        return "limit=" + limit;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public LimitArgument clone() {
        return new LimitArgument(limit);
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String getKey() {
        return "limit";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher matcher = CommandUtils.INT_REGEX.matcher(str);
        if(matcher.lookingAt()) {
            String group = matcher.group();
            return new SelectorArgumentParseResult(group, new LimitArgument(Integer.parseInt(group)));
        }
        throw new IllegalArgumentException("Expected number at: " + str);
    }

    @Override
    public boolean isCompatibleWith(Selector selector) {
        if(selector.getBase() == Selector.BaseSelector.SENDER) throw new IllegalArgumentException("Limit is inapplicable for the " + selector.getBase() + " selector type");
        return SelectorArgument.super.isCompatibleWith(selector);
    }
}
