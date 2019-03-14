package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class DistanceArgument implements SelectorArgument {
    @NotNull
    private final NumberRange<Double> distance;

    public DistanceArgument(@NotNull NumberRange<Double> distance) {
        this.distance = distance;
        if(distance.hasNegative()) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Distance cannot be negative", distance);
        }
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
