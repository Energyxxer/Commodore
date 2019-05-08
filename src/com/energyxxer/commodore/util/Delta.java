package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class Delta {
    public final double x;
    public final double y;
    public final double z;

    public Delta(double size) {
        this(size, size, size);
    }

    public Delta(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

        assertFinite(x, "x");
        assertFinite(y, "y");
        assertFinite(z, "z");
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public String toString() {
        return CommandUtils.numberToPlainString(x) + " " + CommandUtils.numberToPlainString(y) + " " + CommandUtils.numberToPlainString(z);
    }
}
