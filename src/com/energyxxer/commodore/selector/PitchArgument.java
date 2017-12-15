package com.energyxxer.commodore.selector;

public class PitchArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> value;

    public PitchArgument(SelectorNumberArgument<Double> value) {
        this.value = value;
    }

    @Override
    public String getArgumentString() {
        return "y_rotation=" + value;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public PitchArgument clone() {
        return new PitchArgument(value.clone());
    }
}
