package com.energyxxer.commodore;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.ScoreHolder;

public final class CommandUtils {

    public static final String DEFAULT_NAMESPACE = "minecraft";
    public static final String NAMESPACE_ID_SEPARATOR = ":";

    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";

    private CommandUtils() {
    }

    public static String escape(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String escapeIfNecessary(String str) {
        if(!needsEscaping(str)) return str;
        else return "\"" + escape(str) + "\"";
    }

    public static boolean needsEscaping(String str) {
        for(char c : str.toCharArray()) {
            if(!ALPHANUMERIC_CHARACTERS.contains(c + "")) return false;
        }
        return true;
    }

    public static String getRawReference(ScoreHolder scoreHolder, Entity sender) {
        return (scoreHolder instanceof Entity && sender != null) ? ((Entity) scoreHolder).getSelectorAs(sender).toString() : scoreHolder.getReference();
    }

    public static String toString(double num) {
        if((num % 1) == 0) return Integer.toString((int) num);
        else return Double.toString(num);
    }
}
