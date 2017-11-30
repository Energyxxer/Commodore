package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.score.ScoreManager;
import com.energyxxer.commodore.selector.Selector;

public class GenericEntity implements Entity {
    private Selector selector;
    private ScoreManager scoreManager = new ScoreManager(this);

    public GenericEntity(Selector selector) {
        this.selector = selector;
    }

    @Override
    public Selector getSelector() {
        return selector;
    }

    @Override
    public ScoreManager getScoreManager() {
        return scoreManager;
    }
}
