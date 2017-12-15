package com.energyxxer.commodore.selector;

public class LimitArgument implements SelectorArgument {
    private int limit;

    public LimitArgument(int limit) {
        if(limit <= 0) throw new IllegalArgumentException("Limit must be greater than zero: " + limit);
        this.limit = limit;
    }

    @Override
    public String getArgumentString() {
        return "limit=" + limit;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public LimitArgument clone() {
        return new LimitArgument(limit);
    }
}
