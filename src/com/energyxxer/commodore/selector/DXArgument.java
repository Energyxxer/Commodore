package com.energyxxer.commodore.selector;

public class DXArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public DXArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dx=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DXArgument clone() {
        return new DXArgument(value.clone());
    }
}
