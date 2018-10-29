package com.energyxxer.commodore.functionlogic.selector;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;

public class DistanceArgument implements SelectorArgument {

    private final SelectorNumberArgument<Double> distance;

    public DistanceArgument(SelectorNumberArgument<Double> distance) {
        this.distance = distance;
    }

    @Override
    public String getArgumentString() {
        return "distance=" + distance;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DistanceArgument clone() {
        return new DistanceArgument(distance.clone());
    }

    @Override
    public String getKey() {
        return "distance";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.X, ExecutionVariable.Y, ExecutionVariable.Z, ExecutionVariable.DIMENSION);
    }
}
