package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagShort extends NumericNBTTag<Short> {
    private final short value;

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
    public @NotNull NumericNBTTag<Short> scale(double scale) {
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
        return String.valueOf(value) + NumericNBTType.SHORT.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
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
