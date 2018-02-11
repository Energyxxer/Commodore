package com.energyxxer.commodore.nbt;

public abstract class NBTTag {
    protected final String name;

    public static final String ALLOWED_NAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    public NBTTag(String name) {
        this.name = name;
    }

    public abstract String getType();

    public String toHeaderString() {
        boolean needsQuotes = false;

        for(char c : name.toCharArray()) {
            if(!ALLOWED_NAME_CHARACTERS.contains("" + c)) {
                needsQuotes = true;
                break;
            }
        }

        if(needsQuotes) {
            return "\"" + name.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
        } else return name;
    }

    public abstract String toHeadlessString();

    public abstract String toString();

    public abstract NBTTag clone();
}
