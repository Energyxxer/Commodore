package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.SelectorNumberArgument;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class ExecuteConditionScoreMatch extends ExecuteCondition {

    private final LocalScore target;
    private final SelectorNumberArgument range;

    public ExecuteConditionScoreMatch(ConditionType flowController, LocalScore target, SelectorNumberArgument range) {
        super(flowController);
        this.target = target;
        this.range = range;
    }

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
