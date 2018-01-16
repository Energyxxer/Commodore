package com.energyxxer.commodore.selector;

public class DYArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public DYArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "dy=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DYArgument clone() {
        return new DYArgument(value.clone());
    }
}
