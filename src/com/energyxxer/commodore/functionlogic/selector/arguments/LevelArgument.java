package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class LevelArgument implements SelectorArgument {
    @NotNull
    private final NumberRange<@NotNull Integer> value;

    public LevelArgument(@NotNull NumberRange<@NotNull Integer> value) {
        this.value = value;

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
}
