package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionScoreMatch extends ExecuteCondition {
    @NotNull
    private final LocalScore target;
    @NotNull
    private final NumberRange range;

    public ExecuteConditionScoreMatch(@NotNull ConditionType flowController, @NotNull LocalScore target, @NotNull NumberRange range) {
        super(flowController);
        this.target = target;
        this.range = range;

        if (target.getHolder() != null) target.getHolder().assertSingle("TARGET_ENTITY");
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score " + target.getHolder() + " " + target.getObjective().getName() + " matches " + range.toString());
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

    @NotNull
    public LocalScore getTarget() {
        return target;
    }

    @NotNull
    public NumberRange getRange() {
        return range;
    }
}
