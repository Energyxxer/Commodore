package com.energyxxer.commodore.functionlogic.nbt;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum NumericNBTType {
    BYTE("b"), DOUBLE("d"), FLOAT("f"), INT(""), LONG("L"), SHORT("s");

    private final String suffix;

    NumericNBTType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public static NumericNBTType getTypeForSuffix(@NotNull String suffix) {
        for(NumericNBTType type : values()) {
            if(type.suffix.toLowerCase(Locale.ENGLISH).equals(suffix.toLowerCase(Locale.ENGLISH))) return type;
        }
        return null;
    }
}
