package com.energyxxer.commodore.nbt;

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
        return String.valueOf(value) + 's';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @Override
    public TagShort clone() {
        return new TagShort(name, value);
    }
}
