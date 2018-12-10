package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagByte extends NBTTag {
    private final byte value;

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
