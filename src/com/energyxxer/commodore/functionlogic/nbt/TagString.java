package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TagString extends NBTTag {
    private final String value;

    public TagString(@NotNull String value) {
        this("", value);
    }

    public TagString(@NotNull String name, String value) {
        super(name);
        this.value = value;
    }

    @NotNull
    @Override
    public String getType() {
        return "TAG_String";
    }

    @NotNull
    @Override
    public String toHeadlessString() {
        return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @NotNull
    @Override
    public TagString clone() {
        return new TagString(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagString tagString = (TagString) o;
        return Objects.equals(value, tagString.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
