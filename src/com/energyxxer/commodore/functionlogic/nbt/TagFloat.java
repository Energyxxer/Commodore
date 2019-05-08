package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class TagFloat extends NumericNBTTag<Float> {
    private float value;

    public TagFloat() {
        this(0);
    }

    public TagFloat(float value) {
        this("", value);
    }

    public TagFloat(@NotNull String name, float value) {
        super(name);
        this.value = value;
        assertFinite(value, "value");
    }

    @Override
    public @NotNull NumericNBTType getNumericType() {
        return NumericNBTType.FLOAT;
    }

    @NotNull
    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Float value) {
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTTag<Float> scale(double scale) {
        assertFinite(scale, "scale");
        return new TagFloat((float) (value * scale));
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Float";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return value + NumericNBTType.FLOAT.getSuffix();
    }

    @NotNull
    @Override
    public TagFloat clone() {
        return new TagFloat(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagFloat tagFloat = (TagFloat) o;
        return Float.compare(tagFloat.value, value) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
