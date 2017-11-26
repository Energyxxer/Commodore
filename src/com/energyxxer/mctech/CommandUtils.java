package com.energyxxer.mctech;

public final class CommandUtils {

    public static final String DEFAULT_NAMESPACE = "minecraft";
    public static final String NAMESPACE_ID_SEPARATOR = ":";

    public static String escape(String str) {
        return str.replace("\\","\\\\").replace("\"","\\\"");
    }

    public static String toString(double num) {
        if((num % 1) == 0) return Integer.toString((int) num);
        else return Double.toString(num);
    }

    private CommandUtils() {}
}
