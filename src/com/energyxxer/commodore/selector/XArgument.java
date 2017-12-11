package com.energyxxer.commodore.selector;

public class XArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public XArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "x=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}
