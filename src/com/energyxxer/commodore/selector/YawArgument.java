package com.energyxxer.commodore.selector;

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
}
