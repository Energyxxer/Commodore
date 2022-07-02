package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LevelArgument implements SelectorArgument {
    @NotNull
    public final NumberRange<Integer> value;

    public LevelArgument(@NotNull NumberRange<Integer> value) {
        this.value = value;
        value.assertOrdered();

        if(value.hasNegative()) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Level shouldn't be negative", value);
        }
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "level=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public LevelArgument clone() {
        return new LevelArgument(value.clone());
    }

    @NotNull
    @Override
    public String getKey() {
        return "level";
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
        LevelArgument that = (LevelArgument) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
