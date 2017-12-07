package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

public class ScoreSet extends ScoreboardManipulation {

    private LocalScore score;
    private int value;

    public ScoreSet(LocalScore score, int value) {
        super(new ScoreboardAccess(score, ScoreboardAccess.AccessType.WRITE));
        this.score = score;
        this.value = value;
    }

    @Override
    public String getOperationContent(Entity sender) {
        return "scoreboard players set " + CommandUtils.getRawReference(score.getParent().getHolder(), sender) + " " + score.getObjective().getName() + " " + value;
    }
}
