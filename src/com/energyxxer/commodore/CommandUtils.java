package com.energyxxer.commodore;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.ScoreHolder;

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

    public static String getRawReference(ScoreHolder scoreHolder, Entity sender) {
        return (scoreHolder instanceof Entity && sender != null) ? ((Entity) scoreHolder).getSelectorAs(sender).toString() : scoreHolder.getReference();
    }

    private CommandUtils() {}
}
