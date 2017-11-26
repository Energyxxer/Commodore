package com.energyxxer.mctech.nbt;

public class TagShort extends NBTTag {
    private short value;

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
}
