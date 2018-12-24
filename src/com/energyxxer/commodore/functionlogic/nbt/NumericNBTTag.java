package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

public abstract class NumericNBTTag<T extends Number> extends NBTTag {

    public NumericNBTTag(@NotNull String name) {
        super(name);
    }

    @NotNull public abstract NumericNBTType getNumericType();
    @NotNull public abstract T getValue();

    @NotNull public abstract NumericNBTTag<T> scale(double scale);
}
