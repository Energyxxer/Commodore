package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

public class ScoreGet extends ScoreboardManipulation {

    private LocalScore score;

    public ScoreGet(LocalScore score) {
        super(new ScoreboardAccess(score, ScoreboardAccess.AccessType.READ));
        this.score = score;
    }

    @Override
    public String getOperationContent(Entity sender) {
        return "# use score " + score;
    }
}
