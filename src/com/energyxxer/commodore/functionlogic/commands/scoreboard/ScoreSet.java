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

public class ScoreSet implements Command {

    private final LocalScore score;
    private final int value;

    private final ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreSet(LocalScore score, int value) {
        this.score = score;
        this.value = value;

        if(score.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) score.getHolder()).getScoreboardAccesses());
        }

        this.accesses.add(new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.WRITE));
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players set \be0 " + score.getObjective().getName() + " " + value, score.getHolder());
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
