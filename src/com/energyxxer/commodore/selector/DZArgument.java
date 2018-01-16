package com.energyxxer.commodore.selector;

public class DZArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public DZArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dz=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DZArgument clone() {
        return new DZArgument(value.clone());
    }
}
