package com.energyxxer.commodore.functionlogic.nbt;

import java.util.Objects;

public class TagLong extends NBTTag {
    private final long value;

    public TagLong(long value) {
        this("", value);
    }

    public TagLong(String name, long value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_LONG";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.LONG.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @Override
    public TagLong clone() {
        return new TagLong(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagLong tagLong = (TagLong) o;
        return Double.compare(tagLong.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
