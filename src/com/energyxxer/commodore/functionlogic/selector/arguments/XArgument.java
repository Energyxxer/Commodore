package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

public class XArgument implements SelectorArgument {

    private final double value;

    public XArgument(double value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "x=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public XArgument clone() {
        return new XArgument(value);
    }

    @NotNull
    @Override
    public String getKey() {
        return "x";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
