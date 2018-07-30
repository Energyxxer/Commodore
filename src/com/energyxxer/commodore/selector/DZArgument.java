package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class DZArgument implements SelectorArgument {

    private final double value;

    public DZArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dz=" + CommandUtils.toString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DZArgument clone() {
        return new DZArgument(value);
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
