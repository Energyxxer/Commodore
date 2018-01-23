package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class DXArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public DXArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dx=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DXArgument clone() {
        return new DXArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "dx";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.X, ExecutionVariable.DIMENSION);
    }
}
