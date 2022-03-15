package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NumberRange<T extends Number> implements Cloneable {
    @Nullable
    protected final T min;
    @Nullable
    protected final T max;

    @NotNull
    protected final Class<? extends Number> cls;

    protected NumberRange(@NotNull Class<T> cls, @Nullable T min, @Nullable T max) {
        this.min = min;
        this.max = max;

        this.cls = cls;
    }

    public boolean hasNegative() {
        return (min != null && min.doubleValue() < 0) || (max != null && max.doubleValue() < 0);
    }

    public void assertOrdered() {
        assertOrdered("RANGE");
    }

    public void assertOrdered(String causeKey) {
        if(min != null && max != null && (min.doubleValue() > max.doubleValue() || min.longValue() > max.longValue())) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_RANGE_ERROR, "Min cannot be bigger than max; Min: " + min + ", Max: " + max, min, causeKey);
        }
    }

    @NotNull
    public Class<? extends Number> getNumberClass() {
        return cls;
    }

    @Override
    public String toString() {
        if(min != null && min.equals(max)) {
            return CommandUtils.numberToPlainString(min.doubleValue());
        } else if(min == null && max == null) {
            return getSuperRangeString();
        } else {
            return ((min != null) ? CommandUtils.numberToPlainString(min) : "") + ".." + ((max != null) ? "" + CommandUtils.numberToPlainString(max) : "");
        }
    }

    @Override
    @NotNull
    public abstract NumberRange<T> clone();

    public static RangeParseResult<Double> parseDouble(String str) {
        Matcher range = Pattern.compile("(-?\\d+(?:\\.\\d+)?)?\\.\\.(-?\\d+(?:\\.\\d+)?)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Double min = (rawMin != null) ? Double.parseDouble(rawMin) : null;
            Double max = (rawMax != null) ? Double.parseDouble(rawMax) : null;

            return new RangeParseResult<>(range.group(), new DoubleRange(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+(?:\\.\\d+)?").matcher(str);
        if(exact.lookingAt()) {
            return new RangeParseResult<>(exact.group(), new DoubleRange(Double.parseDouble(exact.group())));
        }
        throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "'" + str + "' is not a double or range", str);
    }

    public static RangeParseResult<Integer> parseInt(String str) {
        Matcher range = Pattern.compile("(-?\\d+)?\\.\\.(-?\\d+)?").matcher(str);
        if(range.lookingAt()) {
            MatchResult matchResult = range.toMatchResult();

            String rawMin = matchResult.group(1);
            String rawMax = matchResult.group(2);

            Integer min = (rawMin != null) ? Integer.parseInt(rawMin) : null;
            Integer max = (rawMax != null) ? Integer.parseInt(rawMax) : null;

            return new RangeParseResult<>(range.group(), new IntegerRange(min, max));
        }
        Matcher exact = Pattern.compile("-?\\d+").matcher(str);
        if(exact.lookingAt()) {
            return new RangeParseResult<>(exact.group(), new IntegerRange(Integer.parseInt(exact.group())));
        }
        throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "'" + str + "' is not an integer or range", str);
    }

    @Nullable
    public T getMin() {
        return min;
    }

    @Nullable
    public T getMax() {
        return max;
    }

    protected abstract String getSuperRangeString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberRange<?> that = (NumberRange<?>) o;
        return Objects.equals(min, that.min) &&
                Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}

class RangeParseResult<T extends Number> {
    final String raw;
    final NumberRange<T> result;

    public RangeParseResult(String raw, NumberRange<T> result) {
        this.raw = raw;
        this.result = result;
    }
}