package com.energyxxer.commodore.selector;

public class ZArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public ZArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "z=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}
