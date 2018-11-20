package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.util.NumberRange;

public class LevelArgument implements SelectorArgument {

    private final NumberRange<Integer> value;

    public LevelArgument(NumberRange<Integer> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "level=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public LevelArgument clone() {
        return new LevelArgument(value.clone());
    }

    @Override
    public String getKey() {
        return "level";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
