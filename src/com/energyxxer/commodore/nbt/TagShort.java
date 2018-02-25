package com.energyxxer.commodore.nbt;

import java.util.Objects;

public class TagShort extends NBTTag {
    private final short value;

    public TagShort(int value) {
        this("", value);
    }

    public TagShort(String name, int value) {
        super(name);
        this.value = (short) value;
    }

    public TagShort(short value) {
        this("", value);
    }

    public TagShort(String name, short value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_Short";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.SHORT.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

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
