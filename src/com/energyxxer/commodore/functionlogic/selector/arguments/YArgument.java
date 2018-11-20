
package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;

public class YArgument implements SelectorArgument {

    private final double value;

    public YArgument(double value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "y=" + CommandUtils.numberToPlainString(value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public YArgument clone() {
        return new YArgument(value);
    }

    @Override
    public String getKey() {
        return "y";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
