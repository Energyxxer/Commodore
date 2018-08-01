package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class DXArgument implements SelectorArgument {

    private final double value;

    public DXArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dx=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DXArgument clone() {
        return new DXArgument(value);
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
