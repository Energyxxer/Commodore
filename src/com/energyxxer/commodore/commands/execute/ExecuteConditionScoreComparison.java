package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.commands.scoreboard.ScoreComparison;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ExecuteConditionScoreComparison extends ExecuteCondition {

    private final LocalScore target;
    private final ScoreComparison comparison;
    private final LocalScore source;

    public ExecuteConditionScoreComparison(ConditionType flowController, LocalScore target, ScoreComparison comparison, LocalScore source) {
        super(flowController);
        this.target = target;
        this.comparison = comparison;
        this.source = source;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score \be0 " + target.getObjective().getName() + " " + comparison.getSymbol() + " \be1 " + source.getObjective().getName(), target.getHolder(), source.getHolder());
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>();
        if(target.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) target.getHolder()).getScoreboardAccesses());
        }
        if(source.getHolder() instanceof Entity) {
            accesses.addAll(((Entity) source.getHolder()).getScoreboardAccesses());
        }
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
        return false;
    }
}
