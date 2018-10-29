package com.energyxxer.commodore.functionlogic.nbt;

import java.util.Objects;

public class TagDouble extends NBTTag {
    private final double value;

    public TagDouble(double value) {
        this("", value);
    }

    public TagDouble(String name, double value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_DOUBLE";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.DOUBLE.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

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
