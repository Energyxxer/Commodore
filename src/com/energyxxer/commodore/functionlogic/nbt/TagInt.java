package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagInt extends NBTTag {
    private final int value;

    public TagInt(int value) {
        this("", value);
    }

    public TagInt(@NotNull String name, int value) {
        super(name);
        this.value = value;
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Int";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.INT.getSuffix();
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @NotNull
    @Override
    public TagInt clone() {
        return new TagInt(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagInt tagInt = (TagInt) o;
        return value == tagInt.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
