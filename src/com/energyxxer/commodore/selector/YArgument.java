
package com.energyxxer.commodore.selector;

public class YArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public YArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "y=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public YArgument clone() {
        return new YArgument(value.clone());
    }
}
