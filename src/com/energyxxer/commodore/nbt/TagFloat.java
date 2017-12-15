package com.energyxxer.commodore.nbt;

public class TagFloat extends NBTTag {
    private float value;

    public TagFloat(float value) {
        this("", value);
    }

    public TagFloat(String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_Float";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + 'f';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @Override
    public TagFloat clone() {
        return new TagFloat(name, value);
    }
}
