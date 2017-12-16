package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

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
    public String getOperationContent(Entity sender) {
        return "scoreboard players " + ((delta < 0) ? "remove" : "add") + " " + CommandUtils.getRawReference(score.getHolder(), sender) + " " + score.getObjective().getName() + " " + Math.abs(delta);
    }
}
