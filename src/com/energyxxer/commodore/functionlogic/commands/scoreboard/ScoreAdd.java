package com.energyxxer.commodore.functionlogic.commands.scoreboard;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ScoreAdd implements Command {

    private final LocalScore score;
    private final int delta;

    private final ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

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

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players " + ((delta >= 0) ? "add" : "remove") + " \be0 " + score.getObjective().getName() + " " + Math.abs(delta), score.getHolder());
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
