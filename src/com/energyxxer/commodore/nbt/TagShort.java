package com.energyxxer.commodore.nbt;

public class TagShort extends NBTTag {
    private short value;

    public TagShort(int value) {
        this("", value);
    }

    public TagShort(String name, int value) {
        super(name);
        this.value = (short) value;
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
}
