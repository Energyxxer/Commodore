package com.energyxxer.commodore.score;

public class FakePlayer implements ScoreHolder {
    private String name;
    private ScoreManager scoreManager = new ScoreManager(this);

    public FakePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getReference() {
        return name;
    }

    @Override
    public ScoreManager getScoreManager() {
        return scoreManager;
    }
}
