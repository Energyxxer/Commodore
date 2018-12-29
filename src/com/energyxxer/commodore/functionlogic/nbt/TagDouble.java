package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagDouble extends NumericNBTTag<Double> {
    private double value;

    public TagDouble() {
        this(0);
    }

    public TagDouble(double value) {
        this("", value);
    }

    public TagDouble(@NotNull String name, double value) {
        super(name);
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTType getNumericType() {
        return NumericNBTType.DOUBLE;
    }

    @NotNull
    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Double value) {
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTTag<Double> scale(double scale) {
        return new TagDouble(value * scale);
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_DOUBLE";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.DOUBLE.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @NotNull
    @Override
    public TagDouble clone() {
        return new TagDouble(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDouble tagDouble = (TagDouble) o;
        return Double.compare(tagDouble.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
