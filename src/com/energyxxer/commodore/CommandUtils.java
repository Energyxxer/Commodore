package com.energyxxer.commodore;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Class containing a series of utility methods and constants for common use in commands.
 * */
public final class CommandUtils {

    /**
     * String describing all the characters allowed in a string without the need of quotation marks used
     * in places such as nbt tag keys, entity tags, team names, etc.
     * */
    public static final String IDENTIFIER_ALLOWED = "[A-Za-z0-9_.\\-+]*";

    /**
     * Escapes the given string's quotes and backslashes.
     *
     * @param str The string to be escaped.
     *
     * @return The escaped string.
     * */
    @NotNull
    public static String escape(@NotNull String str) {
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
    @NotNull
    public static String quoteIfNecessary(@NotNull String str) {
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
    public static boolean needsQuoting(@NotNull String str) {
        return !str.matches(IDENTIFIER_ALLOWED);
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
    @NotNull
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
    @NotNull
    public static String numberToStringNoScientific(double num) {
        DecimalFormat df = new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        return df.format(num);
    }

    @NotNull
    public static JsonElement constructRange(@Nullable Integer min, @Nullable Integer max) {
        if(min != null && min.equals(max)) return new JsonPrimitive(min);
        else {
            JsonObject range = new JsonObject();
            if(min != null) range.addProperty("min", min);
            if(max != null) range.addProperty("max", max);
            return range;
        }
    }

    @NotNull
    public static JsonElement constructRange(@Nullable Double min, @Nullable Double max) {
        if(min != null && min.equals(max)) return new JsonPrimitive(min);
        else {
            JsonObject range = new JsonObject();
            if(min != null) range.addProperty("min", min);
            if(max != null) range.addProperty("max", max);
            return range;
        }
    }

    @NotNull
    public static String parseQuotedString(@NotNull String text) {
        int index = 0;
        char delimiter = text.charAt(index);
        if (delimiter != '"' && delimiter != '\'') throw new IllegalArgumentException("Expected string at index " + index);
        index++;
        StringBuilder sb = new StringBuilder();
        boolean escaped = false;
        boolean terminated = false;
        for (; index < text.length(); index++)
        {
            char c = text.charAt(index);
            if (!escaped)
            {
                if (c == '\\')
                {
                    escaped = true;
                    continue;
                }
                else if (c == delimiter)
                {
                    terminated = true;
                    break;
                }
            }
            else
            {
                escaped = false;
                boolean unicodeSequence = false;
                switch (c)
                {
                    case '0': c = '\0'; break;
                    case 'b': c = '\b'; break;
                    case 'f': c = '\f'; break;
                    case 'n': c = '\n'; break;
                    case 'r': c = '\r'; break;
                    case 't': c = '\t'; break;
                    case '\\': break;
                    case '\'': break;
                    case '\"': break;
                    case 'u': unicodeSequence = true; break;
                    default: throw new IllegalArgumentException("Illegal escape sequence");
                }
                if (unicodeSequence)
                {
                    int sequenceLength = 4;
                    if (index + sequenceLength + 1 > text.length()) throw new IllegalArgumentException("Unexpected end of unicode escape sequence");
                    String sequence = text.substring(index + 1, sequenceLength);
                    int code;
                    try {
                        code = Integer.parseInt(sequence, 16);
                    } catch(NumberFormatException x) {
                        throw new IllegalArgumentException("Illegal unicode escape sequence");
                    }
                    String unicode = new String(Character.toChars(code));
                    sb.append(unicode);
                    index += sequenceLength;
                    continue;
                }
            }
            sb.append(c);
        }
        if (!terminated) throw new IllegalArgumentException("Unexpected end of input");
        return sb.toString();
    }

    /**
     * CommandUtils should not be instantiated.
     * */
    private CommandUtils() {
    }
}
