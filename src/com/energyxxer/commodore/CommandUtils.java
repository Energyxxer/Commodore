package com.energyxxer.commodore;

import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class containing a series of utility methods and constants for common use in commands.
 * */
public final class CommandUtils {

    /**
     * String describing all the characters allowed in a string without the need of quotation marks used
     * in places such as objective names, nbt tag keys, entity tags, team names, etc.
     * */
    public static final String IDENTIFIER_ALLOWED = "[A-Za-z0-9_.\\-+]*";

    private static final HashMap<Character, String> ESCAPED = new HashMap<>();

    static {
        ESCAPED.put('\b', "b");
        ESCAPED.put('\n', "n");
        ESCAPED.put('\f', "f");
        ESCAPED.put('\r', "r");
        ESCAPED.put('\t', "t");
    }

    /**
     * Escapes the given string's quotes and backslashes.
     *
     * @param str The string to be escaped.
     *
     * @return The escaped string.
     * */
    @NotNull
    public static String escape(@NotNull String str) {
        return escape(str, VersionFeatureManager.getBoolean("identifiers.escape_unicode", true));
    }

    /**
     * Escapes the given string's quotes and backslashes.
     *
     * @param str The string to be escaped.
     *
     * @return The escaped string.
     * */
    @NotNull
    public static String escape(@NotNull String str, boolean escapeUnicode) {
        str = str.replace("\\", "\\\\").replace("\"", "\\\"");
        StringBuilder sb = new StringBuilder();
        for(char c : str.toCharArray()) {
            if(((int) c) > 127 && escapeUnicode) {
                sb.append("\\u");
                sb.append(padLeft(Integer.toString((int)c, 16).toUpperCase(Locale.ENGLISH), 4, '0'));
            } else if(ESCAPED.containsKey(c)) {
                sb.append("\\");
                sb.append(ESCAPED.get(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String padLeft(@NotNull String str, int minChars, char padChar) {
        int newChars = minChars - str.length();
        if(newChars <= 0) return str;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < newChars; i++) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
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
     * Escapes and wraps the given string in quotes if not all its characters are allowed by commands in an
     * unquoted string. If all characters are allowed, nothing is changed.
     *
     * @param str The string to check and, if needed, quote.
     *
     * @return The given string, quoted, only if it contains a character not allowed in an unquoted string. Otherwise,
     * the returned string is the same as the original.
     * */
    @NotNull
    public static String quoteIfNecessary(@NotNull String str, boolean escapeUnicode) {
        return (needsQuoting(str)) ? "\"" + escape(str, escapeUnicode) + "\"" : str;
    }

    /**
     * Escapes and wraps the given string in quotes if not all its characters are allowed by commands in an
     * unquoted string. If all characters are allowed, nothing is changed.
     *
     * @param str The string to check and, if needed, quote.
     * @param featureKey The feature key from which to retrieve the regex.
     * @param defaultRegex The default regex to use, in case the featureKey is not found in the active feature map.
     *
     * @return The given string, quoted, only if it contains a character not allowed in an unquoted string. Otherwise,
     * the returned string is the same as the original.
     * */
    @NotNull
    public static String quoteIfNecessary(@NotNull String str, @NotNull String featureKey, String defaultRegex) {
        return (needsQuoting(str, featureKey, defaultRegex)) ? "\"" + escape(str) + "\"" : str;
    }

    /**
     * Escapes and wraps the given string in quotes if not all its characters are allowed by commands in an
     * unquoted string. If all characters are allowed, nothing is changed.
     *
     * @param str The string to check and, if needed, quote.
     * @param featureKey0 The feature key from which to retrieve the regex.
     * @param featureKey1 The backup feature key from which to retrieve the regex, if featureKey1 was not found.
     * @param defaultRegex The default regex to use, in case neither featureKey0 nor featureKey1 is not found in the active feature map.
     *
     * @return The given string, quoted, only if it contains a character not allowed in an unquoted string. Otherwise,
     * the returned string is the same as the original.
     * */
    @NotNull
    public static String quoteIfNecessary(@NotNull String str, @NotNull String featureKey0, @NotNull String featureKey1, String defaultRegex) {
        return (needsQuoting(str, featureKey0, featureKey1, defaultRegex)) ? "\"" + escape(str) + "\"" : str;
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
        return needsQuoting(str, "identifiers.regex", CommandUtils.IDENTIFIER_ALLOWED);
    }

    /**
     * Returns whether this character contains any characters disallowed in unquoted strings.
     *
     * @param str The string whose need for quotation is to be tested.
     * @param featureKey The feature key from which to retrieve the regex.
     * @param defaultRegex The default regex to use, in case the featureKey is not found in the active feature map.
     *
     * @return <code>true</code> if this character contains any characters, disallowed in unquoted strings,
     * <code>false</code> if all characters are allowed in unquoted strings.
     * */
    public static boolean needsQuoting(@NotNull String str, @NotNull String featureKey, String defaultRegex) {
        return !str.matches(VersionFeatureManager.getString(featureKey, defaultRegex));
    }

    /**
     * Returns whether this character contains any characters disallowed in unquoted strings.
     *
     * @param str The string whose need for quotation is to be tested.
     * @param featureKey0 The feature key from which to retrieve the regex.
     * @param featureKey1 The backup feature key from which to retrieve the regex, if featureKey1 was not found.
     * @param defaultRegex The default regex to use, in case neither featureKey0 nor featureKey1 is not found in the active feature map.
     *
     * @return <code>true</code> if this character contains any characters, disallowed in unquoted strings,
     * <code>false</code> if all characters are allowed in unquoted strings.
     * */
    public static boolean needsQuoting(@NotNull String str, @NotNull String featureKey0, @NotNull String featureKey1, String defaultRegex) {
        return !str.matches(VersionFeatureManager.getString(featureKey0, VersionFeatureManager.getString(featureKey1, defaultRegex)));
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
    public static String numberToPlainString(Number num) {
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
        if (delimiter != '"' && delimiter != '\'') throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Expected string at index " + index, text);
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
                    default: throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Illegal escape sequence", text);
                }
                if (unicodeSequence)
                {
                    int sequenceLength = 4;
                    if (index + sequenceLength + 1 > text.length()) throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Unexpected end of unicode escape sequence", text);
                    String sequence = text.substring(index + 1, index + 1 + sequenceLength);
                    int code;
                    try {
                        code = Integer.parseInt(sequence, 16);
                    } catch(NumberFormatException x) {
                        throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Illegal unicode escape sequence", text);
                    }
                    String unicode = new String(Character.toChars(code));
                    sb.append(unicode);
                    index += sequenceLength;
                    continue;
                }
            }
            sb.append(c);
        }
        if (!terminated) throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Unexpected end of input", text);
        return sb.toString();
    }

    /**
     * CommandUtils should not be instantiated.
     * */
    private CommandUtils() {
    }

    public static String quote(String str) {
        return quote(str, true);
    }

    public static String quote(String str, boolean escapeUnicode) {
        return "\"" + escape(str, escapeUnicode) + "\"";
    }
}
