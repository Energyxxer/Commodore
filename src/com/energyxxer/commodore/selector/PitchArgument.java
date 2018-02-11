package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class PitchArgument implements SelectorArgument {

    private final SelectorNumberArgument<Double> value;

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
        //Despite being a facing argument, it is not affected in any way by the executing entity's facing;
        //it only matters what the selected entities' facing is.
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        SelectorNumberArgumentParseResult<Double> result = SelectorNumberArgument.parseDouble(str);
        return new SelectorArgumentParseResult(result.raw, new PitchArgument(result.result));
    }
}
