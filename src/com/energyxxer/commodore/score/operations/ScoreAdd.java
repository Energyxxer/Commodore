package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functions.Function;

public class ScoreAdd extends ScoreHolderOperation {

    private int delta;

    public ScoreAdd(LocalScore score, int delta) {
        super(AccessType.ADJUST, score);
        this.delta = delta;
    }

    @Override
    public String getOperationContent(Function function) {
        return "scoreboard players " + ((delta < 0) ? "remove" : "add") + " " + CommandUtils.getRawReference(score.getParent().getHolder(), function.getSender()) + " " + score.getObjective().getName() + " " + Math.abs(delta);
    }
}
