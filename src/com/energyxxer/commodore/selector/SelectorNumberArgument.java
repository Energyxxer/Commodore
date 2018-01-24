package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.CommandUtils;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectorNumberArgument<T extends Number> implements Cloneable {
    private T min;
    private T max;

    public SelectorNumberArgument(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public SelectorNumberArgument(T value) {
        this.min = value;
        this.max = value;
    }

    @Override
    public String toString() {
        if(min != null && max != null && min.equals(max)) {
            return CommandUtils.toString(min.doubleValue());
        } else {
            return ((min != null) ? CommandUtils.toString(min.doubleValue()) : "") + ".." + ((max != null) ? "" + CommandUtils.toString(max.doubleValue()) : "");
        }
    }

    @Override
    public SelectorNumberArgument<T> clone() {
        return new SelectorNumberArgument<>(min, max);
    }

    public static SelectorNumberArgumentParseResult<Double> parseDouble(String str) {
        Matcher range = Pattern.compile("(-?\\d+(?:\\.\\d+)?)?\\.\\.(-?\\d+(?:\\.\\d+)?)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Double min = (rawMin != null) ? Double.parseDouble(rawMin) : null;
            Double max = (rawMax != null) ? Double.parseDouble(rawMax) : null;

            return new SelectorNumberArgumentParseResult<>(range.group(), new SelectorNumberArgument<>(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+(?:\\.\\d+)?").matcher(str);
        if(exact.lookingAt()) {
            return new SelectorNumberArgumentParseResult<>(exact.group(), new SelectorNumberArgument<>(Double.parseDouble(exact.group())));
        }
        throw new IllegalArgumentException("'" + str + "' is not a double or range");
    }

    public static SelectorNumberArgumentParseResult<Integer> parseInt(String str) {
        Matcher range = Pattern.compile("(-?\\d+)?\\.\\.(-?\\d+)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Integer min = (rawMin != null) ? Integer.parseInt(rawMin) : null;
            Integer max = (rawMax != null) ? Integer.parseInt(rawMax) : null;

            return new SelectorNumberArgumentParseResult<>(range.group(), new SelectorNumberArgument<>(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+").matcher(str);
        if(exact.lookingAt()) {
            return new SelectorNumberArgumentParseResult<>(exact.group(), new SelectorNumberArgument<>(Integer.parseInt(exact.group())));
        }
        throw new IllegalArgumentException("'" + str + "' is not an integer or range");
    }
}

class SelectorNumberArgumentParseResult<T extends Number> {
    String raw;
    SelectorNumberArgument<T> result;

    public SelectorNumberArgumentParseResult(String raw, SelectorNumberArgument<T> result) {
        this.raw = raw;
        this.result = result;
    }
}