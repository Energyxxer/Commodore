package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.EntityType;

import java.util.regex.Matcher;

public class TypeArgument implements SelectorArgument {
    private EntityType type;
    private boolean negated;

    public TypeArgument(EntityType type) {
        this(type, false);
    }

    public TypeArgument(EntityType type, boolean negated) {
        this.type = type;
        this.negated = negated;
    }

    public EntityType getType() {
        return type;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "type=" + type;
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
    public TypeArgument clone() {
        return new TypeArgument(type, negated);
    }

    @Override
    public String getKey() {
        return "type";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher delimited = CommandUtils.DELIMITED_STRING_REGEX.matcher(str);
        if(delimited.lookingAt()) {
            String group = delimited.group();
            return new SelectorArgumentParseResult(group, null);
        }
        Matcher simple = CommandUtils.SELECTOR_STRING_REGEX.matcher(str);
        if(simple.lookingAt()) {
            String group = simple.group();
            return new SelectorArgumentParseResult(group, null);
        }
        throw new IllegalArgumentException("Expected string at: " + str);
    }
}
