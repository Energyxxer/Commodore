package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;

public class NameArgument implements SelectorArgument {
    private String name;
    private boolean negated;

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
}
