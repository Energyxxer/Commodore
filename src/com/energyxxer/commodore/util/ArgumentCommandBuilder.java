package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.types.Type;

import java.util.ArrayDeque;
import java.util.Objects;

public class ArgumentCommandBuilder {
    private StringBuilder sb = new StringBuilder();
    private ArrayDeque<String> missingArgs = new ArrayDeque<>();
    private boolean acceptNoMore = false;

    public ArgumentCommandBuilder() {

    }

    public ArgumentCommandBuilder(String initialValue) {
        sb.append(initialValue);
    }

    public ArgumentCommandBuilder append(String s) {
        return append(s, null);
    }

    public ArgumentCommandBuilder append(String s, Object defaultArg) {
        if(Objects.equals(s, defaultArg)) s = null;
        if(s == null) {
            if(defaultArg != null) {
                missingArgs.add(defaultArg.toString());
            } else {
                acceptNoMore = true;
            }
            return this;
        }
        if(acceptNoMore) {
            throw new IllegalArgumentException("No default argument given for missing argument");
        }
        appendDefaultMissingArguments();
        if(sb.length() > 0) sb.append(" ");
        sb.append(s);
        return this;
    }

    public ArgumentCommandBuilder append(CoordinateSet s, Coordinate.DisplayMode mode) {
        if(s == null) {
            missingArgs.add("~ ~ ~");
            return this;
        }
        if(acceptNoMore) {
            throw new IllegalArgumentException("No default argument given for missing argument");
        }
        appendDefaultMissingArguments();
        if(sb.length() > 0) sb.append(" ");
        sb.append(s.getAs(mode));
        return this;
    }

    public ArgumentCommandBuilder appendQuote(String s) {
        return appendQuote(s, null);
    }

    public ArgumentCommandBuilder appendQuote(String s, Object defaultArg) {
        if(Objects.equals(s, defaultArg)) s = null;
        if(s == null) {
            if(defaultArg != null) {
                missingArgs.add(CommandUtils.quoteIfNecessary(defaultArg.toString()));
            } else {
                acceptNoMore = true;
            }
            return this;
        }
        if(acceptNoMore) {
            throw new IllegalArgumentException("No default argument given for missing argument");
        }
        appendDefaultMissingArguments();
        if(sb.length() > 0) sb.append(" ");
        sb.append(CommandUtils.quoteIfNecessary(s));
        return this;
    }

    public ArgumentCommandBuilder append(Object o) {
        return append(o, null);
    }

    public ArgumentCommandBuilder append(Object o, Object defaultArg) {
        return append(o != null ? o.toString() : null, defaultArg != null ? defaultArg.toString() : null);
    }

    public ArgumentCommandBuilder appendLowerCase(Object o) {
        return appendLowerCase(o, null);
    }

    public ArgumentCommandBuilder appendLowerCase(Object o, Object defaultArg) {
        return append(o != null ? o.toString().toLowerCase() : null, defaultArg != null ? defaultArg.toString().toLowerCase() : null);
    }

    public ArgumentCommandBuilder append(Number o) {
        return append(o, null);
    }

    public ArgumentCommandBuilder append(Number o, Number defaultArg) {
        return append(o != null ? CommandUtils.numberToPlainString(o) : null, defaultArg != null ? CommandUtils.numberToPlainString(defaultArg) : null);
    }

    public ArgumentCommandBuilder append(Type o) {
        return append(o != null ? o.toSafeString() : null);
    }

    private void appendDefaultMissingArguments() {
        if(missingArgs.isEmpty()) return;
        while(!missingArgs.isEmpty()) {
            String next = missingArgs.pop();
            if(sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(next);
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}