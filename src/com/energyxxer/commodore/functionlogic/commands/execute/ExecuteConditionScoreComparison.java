package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.scoreboard.ScoreComparison;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionScoreComparison extends ExecuteCondition {
    @NotNull
    private final LocalScore target;
    @NotNull
    private final ScoreComparison comparison;
    @NotNull
    private final LocalScore source;

    public ExecuteConditionScoreComparison(@NotNull ConditionType flowController, @NotNull LocalScore target, @NotNull ScoreComparison comparison, @NotNull LocalScore source) {
        super(flowController);
        this.target = target;
        this.comparison = comparison;
        this.source = source;

        if (target.getHolder() != null) target.getHolder().assertSingle("TARGET_ENTITY");
        if (source.getHolder() != null) target.getHolder().assertSingle("SOURCE_ENTITY");

        target.assertObjectiveNotNull();
        source.assertObjectiveNotNull();
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score " + target.holderToString() + " " + target.objectiveToString() + " " + comparison.getSymbol() + " " + source.holderToString() + " " + source.objectiveToString());
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
    public ScoreComparison getComparison() {
        return comparison;
    }

    @NotNull
    public LocalScore getSource() {
        return source;
    }

    @Override
    public void assertAvailable() {
        target.assertAvailable();
        source.assertAvailable();
    }
}
