package com.energyxxer.commodore.selector;

public class LevelArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public LevelArgument(SelectorNumberArgument<Double> value) {
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
}
