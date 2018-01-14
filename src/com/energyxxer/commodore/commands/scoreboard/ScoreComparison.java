package com.energyxxer.commodore.commands.scoreboard;

public enum ScoreComparison {
    LESS_THAN("<"), LESS_THAN_EQUAL("<="), EQUAL("="), GREATER_THAN_EQUAL(">="), GREATER_THAN(">");

    private final String symbol;

    ScoreComparison(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
