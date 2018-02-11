package com.energyxxer.commodore.nbt;

public class TagString extends NBTTag {
    private final String value;

    public TagString(String value) {
        this("", value);
    }

    public TagString(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_String";
    }

    @Override
    public String toHeadlessString() {
        return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @Override
    public TagString clone() {
        return new TagString(name, value);
    }
}
