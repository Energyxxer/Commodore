package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;
import static com.energyxxer.commodore.util.MiscValidator.assertNumber;

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
        assertNumber(value, "value");
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
        assertFinite(scale, "scale");
        return new TagDouble(value * scale);
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Double";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        if(Double.isInfinite(value)) {
            return (value < 0 ? "-" : "") + "1.0E+10000" + NumericNBTType.DOUBLE.getSuffix();
        }
        return value + NumericNBTType.DOUBLE.getSuffix();
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
