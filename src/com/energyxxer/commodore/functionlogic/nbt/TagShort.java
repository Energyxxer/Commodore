package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class TagShort extends NumericNBTTag<Short> {
    private short value;

    public TagShort() {
        this(0);
    }

    public TagShort(int value) {
        this("", value);
    }

    public TagShort(@NotNull String name, int value) {
        super(name);
        this.value = (short) value;
    }

    public TagShort(short value) {
        this("", value);
    }

    public TagShort(@NotNull String name, short value) {
        super(name);
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTType getNumericType() {
        return NumericNBTType.SHORT;
    }

    @NotNull
    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Short value) {
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTTag<Short> scale(double scale) {
        assertFinite(scale, "scale");
        return new TagShort((short) (value * scale));
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Short";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return value + NumericNBTType.SHORT.getSuffix();
    }

    @NotNull
    @Override
    public TagShort clone() {
        return new TagShort(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagShort tagShort = (TagShort) o;
        return value == tagShort.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
