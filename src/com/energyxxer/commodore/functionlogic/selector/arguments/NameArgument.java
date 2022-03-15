package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public class NameArgument implements SelectorArgument {
    @NotNull
    private final String name;
    private final boolean negated;

    public NameArgument(@NotNull String name) {
        if(name.startsWith("!")) {
            this.name = name.substring(1);
            this.negated = true;
        } else {
            this.name = name;
            this.negated = false;
        }
    }

    public NameArgument(@NotNull String name, boolean negated) {
        this.name = name;
        this.negated = negated;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public boolean isNegated() {
        return negated;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "name=" + (negated ? "!" : "") + CommandUtils.quoteIfNecessary(name);
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @NotNull
    @Override
    public NameArgument clone() {
        return new NameArgument(name, negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "name";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }
}
