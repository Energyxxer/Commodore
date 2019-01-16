package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagLong extends NumericNBTTag<Long> {
    private long value;

    public TagLong() {
        this(0);
    }

    public TagLong(long value) {
        this("", value);
    }

    public TagLong(@NotNull String name, long value) {
        super(name);
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTType getNumericType() {
        return NumericNBTType.LONG;
    }

    @NotNull
    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(@NotNull Long value) {
        this.value = value;
    }

    @Override
    public @NotNull NumericNBTTag<Long> scale(double scale) {
        return new TagLong((long) (value * scale));
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_Long";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + NumericNBTType.LONG.getSuffix();
    }

    @NotNull
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
