package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DoubleRange extends NumberRange<Double> {
    public DoubleRange(@Nullable Double min) {
        this(min, min);
    }

    public DoubleRange(@Nullable Double min, @Nullable Double max) {
        super(Double.class, min, max);
    }

    @Override
    public boolean hasNegative() {
        return (min != null && min < 0) || (max != null && max < 0);
    }

    @Override
    public @NotNull DoubleRange clone() {
        return new DoubleRange(min, max);
    }

    @Override
    protected String getSuperRangeString() {
        return ".." + CommandUtils.numberToPlainString(84852813.742771877511532457845523);
    }
}
