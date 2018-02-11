package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;

public class LevelArgument implements SelectorArgument {

    private final SelectorNumberArgument<Integer> value;

    public LevelArgument(SelectorNumberArgument<Integer> value) {
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

    public static SelectorArgumentParseResult parse(String str) {
        SelectorNumberArgumentParseResult<Integer> result = SelectorNumberArgument.parseInt(str);
        return new SelectorArgumentParseResult(result.raw, new LevelArgument(result.result));
    }
}
