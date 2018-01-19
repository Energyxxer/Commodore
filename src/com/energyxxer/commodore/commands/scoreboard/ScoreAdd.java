package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreAdd implements Command {

    private LocalScore score;
    private int delta;

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreAdd(LocalScore score, int delta) {
        this.score = score;
        this.delta = delta;

        ScoreboardAccess access2 = new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.WRITE);
        ScoreboardAccess access1 = new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.READ, access2);
        accesses.add(access1);
        accesses.add(access2);
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "scoreboard players " + ((delta < 0) ? "remove" : "add") + " " + CommandUtils.getRawReference(score.getHolder(), sender) + " " + score.getObjective().getName() + " " + Math.abs(delta);
    }
}
