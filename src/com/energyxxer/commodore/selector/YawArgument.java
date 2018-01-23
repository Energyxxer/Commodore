package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class YawArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public YawArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "x_rotation=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public YawArgument clone() {
        return new YawArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "x_rotation";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        //Despite being a rotation argument, it is not affected in any way by the executing entity's rotation;
        //it only matters what the selected entities' rotation is.
        return null;
    }
}
