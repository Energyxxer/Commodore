package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NameArgument implements SelectorArgument {
    @NotNull
    public final String name;
    public final boolean negated;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameArgument that = (NameArgument) o;
        return negated == that.negated &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, negated);
    }
}
