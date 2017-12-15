package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;

public class TagArgument implements SelectorArgument {
    private String tag;
    private boolean negated;

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
}
