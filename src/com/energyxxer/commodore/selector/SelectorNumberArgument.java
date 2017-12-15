package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;

public class SelectorNumberArgument<T extends Number> implements Cloneable {
    private T min;
    private T max;

    public SelectorNumberArgument(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public SelectorNumberArgument(T value) {
        this.min = value;
        this.max = value;
    }

    @Override
    public String toString() {
        if(min != null && max != null && min.equals(max)) {
            return CommandUtils.toString(min.doubleValue());
        } else {
            return ((min != null) ? CommandUtils.toString(min.doubleValue()) : "") + ".." + ((max != null) ? "" + CommandUtils.toString(max.doubleValue()) : "");
        }
    }

    @Override
    public SelectorNumberArgument<T> clone() {
        return new SelectorNumberArgument<T>(min, max);
    }
}
