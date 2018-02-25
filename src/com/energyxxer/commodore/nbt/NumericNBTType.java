package com.energyxxer.commodore.nbt;

public enum NumericNBTType {
    BYTE("b"), DOUBLE("d"), FLOAT("f"), INT(""), LONG("L"), SHORT("s");

    private final String suffix;

    NumericNBTType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
