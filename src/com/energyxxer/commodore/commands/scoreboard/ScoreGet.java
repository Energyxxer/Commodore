package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreGet implements Command {

    private LocalScore score;

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreGet(LocalScore score) {
        this.score = score;

        accesses.add(new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.READ));
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "# use score " + score;
    }
}
