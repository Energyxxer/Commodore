package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class YawArgument implements SelectorArgument {
    @NotNull
    private final NumberRange<@NotNull Double> value;

    public YawArgument(@NotNull NumberRange<@NotNull Double> value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "x_rotation=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public YawArgument clone() {
        return new YawArgument(value.clone());
    }

    @NotNull
    @Override
    public String getKey() {
        return "x_rotation";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        //Despite being a facing argument, it is not affected in any way by the executing entity's facing;
        //it only matters what the selected entities' facing is.
        return null;
    }
}
