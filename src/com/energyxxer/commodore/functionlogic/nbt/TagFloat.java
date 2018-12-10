package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagFloat extends NBTTag {
    private final float value;

    public TagFloat(float value) {
        this("", value);
    }

    public TagFloat(@NotNull String name, float value) {
        super(name);
        this.value = value;
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Float";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.FLOAT.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
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
