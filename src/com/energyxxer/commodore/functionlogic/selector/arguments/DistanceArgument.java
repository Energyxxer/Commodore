package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class DistanceArgument implements SelectorArgument {
    @NotNull
    private final NumberRange<@NotNull Double> distance;

    public DistanceArgument(@NotNull NumberRange<@NotNull Double> distance) {
        this.distance = distance;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "distance=" + distance;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public DistanceArgument clone() {
        return new DistanceArgument(distance.clone());
    }

    @NotNull
    @Override
    public String getKey() {
        return "distance";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.X, ExecutionVariable.Y, ExecutionVariable.Z, ExecutionVariable.DIMENSION);
    }
}
