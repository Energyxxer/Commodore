package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

public abstract class NBTTag {
    @NotNull
    protected String name;

    private static final String ALLOWED_NAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    public NBTTag(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public abstract String getType();

    @NotNull
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

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public abstract String toHeadlessString();

    @Override
    public String toString() {
        return this.toHeaderString() + ":" + this.toHeadlessString();
    }

    @NotNull
    public abstract NBTTag clone();
}
