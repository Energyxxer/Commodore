package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreSet implements Command {

    private LocalScore score;
    private int value;

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreSet(LocalScore score, int value) {
        this.score = score;
        this.value = value;

        this.accesses.add(new ScoreboardAccess(score, ScoreboardAccess.AccessType.WRITE));
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "scoreboard players set " + CommandUtils.getRawReference(score.getHolder(), sender) + " " + score.getObjective().getName() + " " + value;
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }
}
