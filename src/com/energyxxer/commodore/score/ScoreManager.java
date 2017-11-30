package com.energyxxer.commodore.score;

public class ScoreManager {
    private ScoreHolder holder;

    public ScoreManager(ScoreHolder holder) {
        this.holder = holder;
    }

    public ScoreHolder getHolder() {
        return holder;
    }
}
