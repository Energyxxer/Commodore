package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

public class ScoreGet extends ScoreboardManipulation {

    private LocalScore score;

    public ScoreGet(LocalScore score) {
        this.score = score;

        this.addAccesses(new ScoreboardAccess(this, score, ScoreboardAccess.AccessType.READ));
    }

    @Override
    public String getOperationContent(Entity sender) {
        return "# use score " + score;
    }
}
