package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagByte extends NumericNBTTag<Byte> {
    private byte value;

    public TagByte() {
        this(0);
    }

    public TagByte(int value) {
        this((byte) value);
    }

    public TagByte(byte value) {
        this("", value);
    }

    public TagByte(@NotNull String name, int value) {
        this(name, (byte) value);
    }

    public TagByte(@NotNull String name, byte value) {
        super(name);
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTType getNumericType() {
        return NumericNBTType.BYTE;
    }

    @NotNull
    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Byte value) {
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTTag<Byte> scale(double scale) {
        return new TagByte((byte) (value * scale));
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Byte";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.BYTE.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @NotNull
    @Override
    public TagByte clone() {
        return new TagByte(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagByte tagByte = (TagByte) o;
        return value == tagByte.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
