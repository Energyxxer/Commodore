package com.energyxxer.commodore.nbt;

public class TagByte extends NBTTag {
    private byte value;

    public TagByte(int value) {
        this((byte) value);
    }

    public TagByte(byte value) {
        this("", value);
    }

    public TagByte(String name, int value) {
        this(name, (byte) value);
    }

    public TagByte(String name, byte value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getType() {
        return "TAG_Byte";
    }

    @Override
    public String toHeadlessString() {
        return String.valueOf(value) + 'b';
    }

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }
}
