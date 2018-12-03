package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ExecuteStoreScore extends ExecuteStore {
    private final LocalScore score;

    private Collection<ScoreboardAccess> accesses = null;

    public ExecuteStoreScore(LocalScore score) {
        this.score = score;
    }

    public ExecuteStoreScore(StoreValue storeValue, LocalScore score) {
        super(storeValue);
        this.score = score;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score \be0 " + score.getObjective().getName(), score.getHolder());
    }

    private void createScoreboardAccesses() {
        if(accesses != null) return;
        accesses = new ArrayList<>();
        if(score.getHolder() instanceof Entity) accesses.addAll(((Entity) score.getHolder()).getScoreboardAccesses());
        accesses.add(new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.WRITE));
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(accesses == null) createScoreboardAccesses();
        return accesses;
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return true;
    }
}
