package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functions.Function;

public class ScoreAdd extends ScoreboardManipulation {

    private LocalScore score;
    private int delta;

    public ScoreAdd(LocalScore score, int delta) {
        this.score = score;
        this.delta = delta;

        ScoreboardAccess access1 = new ScoreboardAccess(score, ScoreboardAccess.AccessType.READ);
        ScoreboardAccess access2 = new ScoreboardAccess(score, ScoreboardAccess.AccessType.WRITE, access1);
        this.addAccesses(access1, access2);
    }

    @Override
    public String getOperationContent(Function function) {
        return "scoreboard players " + ((delta < 0) ? "remove" : "add") + " " + CommandUtils.getRawReference(score.getParent().getHolder(), function.getSender()) + " " + score.getObjective().getName() + " " + Math.abs(delta);
    }
}
