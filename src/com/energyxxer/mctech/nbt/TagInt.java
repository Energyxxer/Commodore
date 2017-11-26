package com.energyxxer.mctech.nbt;

public class TagInt extends NBTTag {
    private int value;

    public TagInt(int value) {
        this("", value);
    }

    public TagInt(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_Int";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }
}
