package com.energyxxer.commodore.functionlogic.commands.scoreboard;

public enum ScoreComparison {
    LESS_THAN("<"), LESS_THAN_EQUAL("<="), EQUAL("="), GREATER_THAN_EQUAL(">="), GREATER_THAN(">");

    private final String symbol;
    private ScoreComparison reverse;

    static {
        LESS_THAN.reverse = GREATER_THAN_EQUAL;
        LESS_THAN_EQUAL.reverse = GREATER_THAN;
        GREATER_THAN.reverse = LESS_THAN_EQUAL;
        GREATER_THAN_EQUAL.reverse = LESS_THAN;
        EQUAL.reverse = EQUAL;
    }

    ScoreComparison(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

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
