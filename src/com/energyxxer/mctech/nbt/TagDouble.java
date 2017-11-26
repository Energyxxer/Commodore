package com.energyxxer.mctech.nbt;

public class TagDouble extends NBTTag {
    private double value;

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
        return String.valueOf(value) + 'd';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }
}
