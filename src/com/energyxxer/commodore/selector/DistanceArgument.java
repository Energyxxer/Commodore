package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

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

    public static SelectorArgumentParseResult parse(String str) {
        SelectorNumberArgumentParseResult<Double> result = SelectorNumberArgument.parseDouble(str);
        return new SelectorArgumentParseResult(result.raw, new DistanceArgument(result.result));
    }
}
