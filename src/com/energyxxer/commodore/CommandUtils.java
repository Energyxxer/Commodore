package com.energyxxer.commodore;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Class containing a series of utility methods and constants for common use in commands.
 * */
public final class CommandUtils {

    /**
     * String describing all the characters allowed in a string without the need of quotation marks.
     * */
    private static final String STRING_ALLOWED = "[A-Za-z0-9_.\\-+]+";

    /**
     * Escapes the given string's quotes and backslashes.
     *
     * @param str The string to be escaped.
     *
     * @return The escaped string.
     * */
    public static String escape(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    /**
     * Escapes and wraps the given string in quotes if not all its characters are allowed by commands in an
     * unquoted string. If all characters are allowed, nothing is changed.
     *
     * @param str The string to check and, if needed, quote.
     *
     * @return The given string, quoted, only if it contains a character not allowed in an unquoted string. Otherwise,
     * the returned string is the same as the original.
     * */
    public static String quoteIfNecessary(String str) {
        return (needsQuoting(str)) ? "\"" + escape(str) + "\"" : str;
    }

    /**
     * Returns whether this character contains any characters disallowed in unquoted strings.
     *
     * @param str The string whose need for quotation is to be tested.
     *
     * @return <code>true</code> if this character contains any characters, disallowed in unquoted strings,
     * <code>false</code> if all characters are allowed in unquoted strings.
     * */
    public static boolean needsQuoting(String str) {
        return !str.matches(STRING_ALLOWED);
    }

    /**
     * Converts the given number into its plain string representation. This method differs from
     * {@link Double#toString()} on two aspects:
     *
     * <ol>
     *     <li>Whole numbers will be displayed without the decimal places.</li>
     *     <li>Scientific notation will not be used for big nor small numbers.</li>
     * </ol>
     *
     * @param num The number to turn into a plain number string.
     *
     * @return The number, as a plain number string.
     * */
    public static String numberToPlainString(double num) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        return df.format(num);
    }

    /**
     * Converts the given number into its plain string representation. This method differs from
     * {@link Double#toString()} in that scientific notation will not be used.
     *
     * @param num The number to turn into a non-scientific number string.
     *
     * @return The number, as a non-scientific number string.
     * */
    public static String numberToStringNoScientific(double num) {
        DecimalFormat df = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        return df.format(num);
    }

    public static JsonElement constructRange(Integer min, Integer max) {
        if(min != null && min.equals(max)) return new JsonPrimitive(min);
        else {
            JsonObject range = new JsonObject();
            if(min != null) range.addProperty("min", min);
            if(max != null) range.addProperty("max", max);
            return range;
        }
    }

    public static JsonElement constructRange(Double min, Double max) {
        if(min != null && min.equals(max)) return new JsonPrimitive(min);
        else {
            JsonObject range = new JsonObject();
            if(min != null) range.addProperty("min", min);
            if(max != null) range.addProperty("max", max);
            return range;
        }
    }

    /**
     * CommandUtils should not be instantiated.
     * */
    private CommandUtils() {
    }
}
