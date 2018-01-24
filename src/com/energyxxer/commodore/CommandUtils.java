package com.energyxxer.commodore;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.ScoreHolder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;

public final class CommandUtils {

    public static final String DEFAULT_NAMESPACE = "minecraft";
    public static final String NAMESPACE_ID_SEPARATOR = ":";

    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";

    public static final Pattern DOUBLE_REGEX = Pattern.compile("-?\\d+(?:\\.\\d+)?");
    public static final Pattern INT_REGEX = Pattern.compile("-?\\d+");
    public static final Pattern SELECTOR_STRING_REGEX = Pattern.compile("[\\w\\.!]+");
    public static final Pattern DELIMITED_STRING_REGEX = Pattern.compile("(['\"]).+?((?<!\\\\)((\\\\\\\\)+)*\\1)");

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
            if(!ALPHANUMERIC_CHARACTERS.contains(c + "")) return true;
        }
        return false;
    }

    public static String unescape(String str) {
        //Remove delimiters if necessary
        String[] delimiters = new String[] {"\"", "'"};
        for(String delimiter : delimiters) {
            if(str.startsWith(delimiter)) {
                if(str.endsWith(delimiter)) {
                    str = str.substring(1,str.length()-1);
                } else throw new IllegalArgumentException("Unclosed string at: " + str);
            }
        }

        StringBuilder sb = new StringBuilder();

        boolean escaped = false;
        for(char c : str.toCharArray()) {
            if(escaped) {
                switch(c) {
                    case 'n': {
                        sb.append('\n');
                        break;
                    }
                    case 'b': {
                        sb.append('\b');
                        break;
                    }
                    case 'r': {
                        sb.append('\r');
                        break;
                    }
                    default: {
                        sb.append(c);
                    }
                }
                escaped = false;
            } else if(c == '\\') {
                escaped = true;
            } else sb.append(c);
        }

        return sb.toString();
    }

    public static String getRawReference(ScoreHolder scoreHolder, Entity sender) {
        return (scoreHolder instanceof Entity && sender != null) ? ((Entity) scoreHolder).getSelectorAs(sender).toString() : scoreHolder.getReference();
    }

    public static String toString(double num) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        return df.format(num);
    }
}
