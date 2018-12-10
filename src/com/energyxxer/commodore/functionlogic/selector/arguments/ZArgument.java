package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public class ZArgument implements SelectorArgument {

    private final double value;

    public ZArgument(double value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "z=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public ZArgument clone() {
        return new ZArgument(value);
    }

    @NotNull
    @Override
    public String getKey() {
        return "z";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
