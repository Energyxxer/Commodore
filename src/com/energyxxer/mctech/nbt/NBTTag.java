package com.energyxxer.mctech.nbt;

public abstract class NBTTag {
    protected String name;

    public static final String ALLOWED_NAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    public NBTTag(String name) {
        this.name = name;
    }

    public abstract String getType();

    public String toHeaderString() {
        boolean needsQuotes = false;

        for(char c : name.toCharArray()) {
            if(!ALLOWED_NAME_CHARACTERS.contains(""+c)) {
                needsQuotes = true;
                break;
            }
        }

        if(needsQuotes) {
            StringBuilder sb = new StringBuilder("\"");
            sb.append(name.replace("\\","\\\\").replace("\"","\\\""));
            sb.append("\"");
            return sb.toString();
        } else return name;
    }

    public abstract String toHeadlessString();

    public abstract String toString();
}
