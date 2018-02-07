package com.energyxxer.commodore.commands.scoreboard;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreAdd implements Command {

    private LocalScore score;
    private int delta;

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreAdd(LocalScore score, int delta) {
        this.score = score;
        this.delta = delta;

        if(score.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) score.getHolder()).getScoreboardAccesses());
        }

        ScoreboardAccess access2 = new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.WRITE);
        ScoreboardAccess access1 = new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.READ, access2);
        accesses.add(access1);
        accesses.add(access2);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players add \be0 " + score.getObjective().getName() + " " + delta, score.getHolder());
    }
}
