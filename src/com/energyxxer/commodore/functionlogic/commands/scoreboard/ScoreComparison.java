package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ScoreComparison {
    LESS_THAN("<"), LESS_THAN_EQUAL("<="), EQUAL("="), GREATER_THAN_EQUAL(">="), GREATER_THAN(">");

    @NotNull
    private final String symbol;
    @Nullable
    private ScoreComparison reverse;

    static {
        LESS_THAN.reverse = GREATER_THAN_EQUAL;
        LESS_THAN_EQUAL.reverse = GREATER_THAN;
        GREATER_THAN.reverse = LESS_THAN_EQUAL;
        GREATER_THAN_EQUAL.reverse = LESS_THAN;
        EQUAL.reverse = EQUAL;
    }

    ScoreComparison(@NotNull String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    public String getSymbol() {
        return symbol;
    }

    @Nullable
    public ScoreComparison getReverse() {
        return reverse;
    }

    public static ScoreComparison getValueForSymbol(String sym) {
        for(ScoreComparison value : values()) {
            if(value.symbol.equals(sym)) return value;
        }
        return null;
    }
}
