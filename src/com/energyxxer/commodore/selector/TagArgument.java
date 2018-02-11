package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

import java.util.regex.Matcher;

public class TagArgument implements SelectorArgument {
    private final String tag;
    private final boolean negated;

    public TagArgument(String tag) {
        if(tag.startsWith("!")) {
            this.tag = tag.substring(1);
            this.negated = true;
        } else {
            this.tag = tag;
            this.negated = false;
        }
    }

    public TagArgument(String tag, boolean negated) {
        this.tag = tag;
        this.negated = negated;
    }

    public String getTag() {
        return tag;
    }

    public boolean isNegated() {
        return negated;
    }

    @Override
    public String getArgumentString() {
        return "tag=" + (negated ? "!" : "") + CommandUtils.escapeIfNecessary(tag);
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
    public TagArgument clone() {
        return new TagArgument(tag, negated);
    }

    @Override
    public String getKey() {
        return "tag";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        Matcher delimited = CommandUtils.DELIMITED_STRING_REGEX.matcher(str);
        if(delimited.lookingAt()) {
            String group = delimited.group();
            return new SelectorArgumentParseResult(group, new TagArgument(CommandUtils.unescape(group)));
        }
        Matcher simple = CommandUtils.SELECTOR_STRING_REGEX.matcher(str);
        if(simple.lookingAt()) {
            String group = simple.group();
            return new SelectorArgumentParseResult(group, new TagArgument(group));
        }
        throw new IllegalArgumentException("Expected string at: " + str);
    }
}
