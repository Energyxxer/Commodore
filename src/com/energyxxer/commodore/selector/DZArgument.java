package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class DZArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public DZArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dz=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DZArgument clone() {
        return new DZArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "dz";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.Z, ExecutionVariable.DIMENSION);
    }
}
