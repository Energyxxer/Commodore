package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommodoreException;

import java.util.Locale;

public class MiscValidator {
    public static void assertFinite(double value, String paramName) {
        if (!Double.isFinite(value)) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Expected finite number for parameter '" + paramName + "', instead got " + value, value, paramName.toUpperCase(Locale.ENGLISH));
        }
    }

    public static void assertNumber(double value, String paramName) {
        if (!Double.isNaN(value)) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Expected non-NaN number for parameter '" + paramName + "', instead got " + value, value, paramName.toUpperCase(Locale.ENGLISH));
        }
    }
}
