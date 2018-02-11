package com.energyxxer.commodore.score;

public class MacroScoreHolder {
    private final String descriptor;

    public MacroScoreHolder() {
        this("");
    }

    public MacroScoreHolder(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "$[" + descriptor + "]";
    }
}
