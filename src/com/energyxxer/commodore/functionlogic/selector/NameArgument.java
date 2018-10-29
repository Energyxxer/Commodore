package com.energyxxer.commodore.functionlogic.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;

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
        return "name=" + (negated ? "!" : "") + CommandUtils.quoteIfNecessary(name);
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
}
