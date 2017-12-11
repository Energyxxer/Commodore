package com.energyxxer.commodore.rotation;

import com.energyxxer.commodore.CommandUtils;

public class RotationUnit {

    public enum Type {
        ABSOLUTE(""), RELATIVE("~");

        public final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }
    }

    private Type type;
    private double value;

    public RotationUnit(double value) {
        this(Type.ABSOLUTE, value);
    }

    public RotationUnit(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    public boolean isIdempotent() {
        return type == Type.ABSOLUTE;
    }

    public boolean isSignificant() {
        return type == Type.ABSOLUTE || value != 0;
    }

    @Override
    public String toString() {
        return type.prefix + CommandUtils.toString(value);
    }
}
