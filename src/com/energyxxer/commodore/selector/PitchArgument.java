package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class PitchArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public PitchArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "y_rotation=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public PitchArgument clone() {
        return new PitchArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "y_rotation";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        //Despite being a rotation argument, it is not affected in any way by the executing entity's rotation;
        //it only matters what the selected entities' rotation is.
        return null;
    }
}
