package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class SortArgument implements SelectorArgument {

    public enum SortType {
        NEAREST(true), FURTHEST(true), RANDOM(false), ARBITRARY(false);

        private final boolean positionSensitive;

        SortType(boolean positionSensitive) {
            this.positionSensitive = positionSensitive;
        }

        public boolean isPositionSensitive() {
            return positionSensitive;
        }

    }
    private final SortType type;

    public SortArgument(SortType type) {
        this.type = type;
    }

    public SortType getSortType() {
        return type;
    }

    @Override
    public String getArgumentString() {
        return "sort=" + type.toString().toLowerCase();
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public SortArgument clone() {
        return new SortArgument(type);
    }

    @Override
    public String getKey() {
        return "sort";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return (type.isPositionSensitive() ? new ExecutionVariableMap(ExecutionVariable.X, ExecutionVariable.Y, ExecutionVariable.Z) : null);
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher delimited = CommandUtils.DELIMITED_STRING_REGEX.matcher(str);
        if(delimited.lookingAt()) {
            String group = delimited.group();
            return new SelectorArgumentParseResult(group, new SortArgument(SortType.valueOf(CommandUtils.unescape(group.toUpperCase()))));
        }
        Matcher simple = CommandUtils.SELECTOR_STRING_REGEX.matcher(str);
        if(simple.lookingAt()) {
            String group = simple.group();
            return new SelectorArgumentParseResult(group, new SortArgument(SortType.valueOf(group.toUpperCase())));
        }
        throw new IllegalArgumentException("Expected string at: " + str);
    }

    @Override
    public boolean isCompatibleWith(Selector selector) {
        if(selector.getBase() == Selector.BaseSelector.SENDER) throw new IllegalArgumentException("Sort is inapplicable for the " + selector.getBase() + " selector type");
        return SelectorArgument.super.isCompatibleWith(selector);
    }
}
