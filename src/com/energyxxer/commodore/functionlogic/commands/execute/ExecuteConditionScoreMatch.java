package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class ExecuteConditionScoreMatch extends ExecuteCondition {
    @NotNull
    private final LocalScore target;
    @NotNull
    private final NumberRange range;

    public ExecuteConditionScoreMatch(@NotNull ConditionType flowController, @NotNull LocalScore target, @NotNull NumberRange range) {
        super(flowController);
        this.target = target;
        this.range = range;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score \be0 " + target.getObjective().getName() + " matches " + range.toString(), target.getHolder());
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return (target.getHolder() instanceof Entity) ? ((Entity) target.getHolder()).getScoreboardAccesses() : Collections.emptyList();
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
