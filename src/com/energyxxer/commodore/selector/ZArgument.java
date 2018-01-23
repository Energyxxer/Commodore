package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ZArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public ZArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "z=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public ZArgument clone() {
        return new ZArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "z";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
