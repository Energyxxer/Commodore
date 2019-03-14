package com.energyxxer.commodore.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IntegerRange extends NumberRange<Integer> {
    public IntegerRange(@Nullable Integer min) {
        this(min, min);
    }

    public IntegerRange(@Nullable Integer min, @Nullable Integer max) {
        super(Integer.class, min, max);
    }

    @Override
    public boolean hasNegative() {
        return (min != null && min < 0) || (max != null && max < 0);
    }

    @Override
    public @NotNull IntegerRange clone() {
        return new IntegerRange(this.min, this.max);
    }

    @Override
    protected String getSuperRangeString() {
        return Integer.MIN_VALUE + "..";
    }
}
