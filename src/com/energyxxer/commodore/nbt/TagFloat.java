package com.energyxxer.commodore.nbt;

import java.util.Objects;

public class TagFloat extends NBTTag {
    private final float value;

    public TagFloat(float value) {
        this("", value);
    }

    public TagFloat(String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_Float";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.FLOAT.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

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
