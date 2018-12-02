package com.energyxxer.commodore.functionlogic.rotation;

import com.energyxxer.commodore.CommandUtils;

import java.util.Objects;

public class RotationUnit {

    public enum Type {
        ABSOLUTE(""), RELATIVE("~");

        public final String prefix;

        Type(String prefix) {
            this.prefix = prefix;
        }
    }

    private final Type type;
    private final double value;

    public RotationUnit(double value) {
        this(Type.ABSOLUTE, value);
    }

    public RotationUnit(Type type, double value) {
        this.type = type;
        this.value = value;
    }

    public boolean isIdempotent() {
        return isAbsolute();
    }

    public boolean isSignificant() {
        return type == Type.ABSOLUTE || value != 0;
    }

    public boolean isAbsolute() {
        return type == Type.ABSOLUTE;
    }

    @Override
    public String toString() {
        return type.prefix + ((value == 0 && type == Type.RELATIVE) ? "" : CommandUtils.numberToPlainString(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotationUnit that = (RotationUnit) o;
        return Double.compare(that.value, value) == 0 &&
                type == that.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, value);
    }
}
