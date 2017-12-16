package com.energyxxer.commodore.score;

public class FakePlayer implements ScoreHolder {
    private String name;

    public FakePlayer(String name) {
        this.name = name;
    }

    @Override
    public String getReference() {
        return name;
    }
}
