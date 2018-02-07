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

public class ScoreSet implements Command {

    private LocalScore score;
    private int value;

    private ArrayList<ScoreboardAccess> accesses = new ArrayList<>();

    public ScoreSet(LocalScore score, int value) {
        this.score = score;
        this.value = value;

        if(score.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) score.getHolder()).getScoreboardAccesses());
        }

        this.accesses.add(new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.WRITE));
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "scoreboard players set \be0 " + score.getObjective().getName() + " " + value, score.getHolder());
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }
}
