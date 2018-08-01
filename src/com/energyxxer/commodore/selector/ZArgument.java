package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class ZArgument implements SelectorArgument {

    private final double value;

    public ZArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "z=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public ZArgument clone() {
        return new ZArgument(value);
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
