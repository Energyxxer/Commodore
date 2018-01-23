package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class XArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public XArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "x=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public XArgument clone() {
        return new XArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "x";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
