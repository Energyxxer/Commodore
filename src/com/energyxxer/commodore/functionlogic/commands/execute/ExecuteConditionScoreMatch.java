package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.ScoreArgument;
import com.energyxxer.commodore.module.settings.ModuleSettingsManager;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionScoreMatch extends ExecuteCondition {
    @NotNull
    private final LocalScore target;
    @NotNull
    private final NumberRange<Integer> range;

    public ExecuteConditionScoreMatch(@NotNull ConditionType flowController, @NotNull LocalScore target, @NotNull NumberRange<Integer> range) {
        super(flowController);
        this.target = target;
        this.range = range;
        range.assertOrdered();

        if (target.getHolder() != null) target.getHolder().assertSingle("TARGET_ENTITY");

        target.assertObjectiveNotNull();
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        if(this.type == ConditionType.IF && ModuleSettingsManager.getActive().CONVERT_IF_SCORE_SENDER_MATCHES_TO_IF_ENTITY.getValue()) {
            if(target.getHolder() instanceof Selector) {
                Selector selector = (Selector) target.getHolder();
                if(selector.getBase() == Selector.BaseSelector.SENDER && selector.getAllArguments().size() == 0) {
                    ScoreArgument scoreArg = new ScoreArgument();
                    scoreArg.put(target.getObjective(), range);
                    Selector newSelector = new Selector(selector.getBase());
                    newSelector.addArgument(scoreArg);

                    return new SubCommandResult(execContext, this.getStarter() + "entity " + newSelector);
                }
            }
        }
        return new SubCommandResult(execContext, this.getStarter() + "score " + target.holderToString() + " " + target.objectiveToString() + " matches " + range.toString());
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
    public NumberRange<Integer> getRange() {
        return range;
    }

    @Override
    public void assertAvailable() {
        target.assertAvailable();
    }
}
