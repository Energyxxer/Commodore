package com.energyxxer.commodore.selector;

public class DistanceArgument implements SelectorArgument {

    private SelectorNumberArgument<Double> distance;

    public DistanceArgument(SelectorNumberArgument<Double> distance) {
        this.distance = distance;
    }

    @Override
    public String getArgumentString() {
        return "distance=" + distance;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DistanceArgument clone() {
        return new DistanceArgument(distance.clone());
    }
}
