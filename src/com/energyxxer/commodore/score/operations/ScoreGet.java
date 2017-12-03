package com.energyxxer.commodore.score.operations;

import com.energyxxer.commodore.functions.Function;

public class ScoreGet extends ScoreboardManipulation {

    private LocalScore score;

    public ScoreGet(LocalScore score) {
        super(new ScoreboardAccess(score, ScoreboardAccess.AccessType.READ));
        this.score = score;
    }

    @Override
    public String getOperationContent(Function function) {
        return "# use score " + score;
    }
}
