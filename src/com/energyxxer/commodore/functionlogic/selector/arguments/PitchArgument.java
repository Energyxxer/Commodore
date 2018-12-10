package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class PitchArgument implements SelectorArgument {
    @NotNull
    private final NumberRange<@NotNull Double> value;

    public PitchArgument(@NotNull NumberRange<@NotNull Double> value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "y_rotation=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public PitchArgument clone() {
        return new PitchArgument(value.clone());
    }

    @NotNull
    @Override
    public String getKey() {
        return "y_rotation";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        //Despite being a facing argument, it is not affected in any way by the executing entity's facing;
        //it only matters what the selected entities' facing is.
        return null;
    }
}
