package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ExecuteStoreScore extends ExecuteStore {
    @NotNull
    private final LocalScore score;

    public ExecuteStoreScore(@NotNull LocalScore score) {
        this.score = score;
    }

    public ExecuteStoreScore(@NotNull StoreValue storeValue, @NotNull LocalScore score) {
        super(storeValue);
        this.score = score;

        score.assertObjectiveNotNull();
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "score " + score.holderToString() + " " + score.objectiveToString());
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

    @NotNull
    public LocalScore getScore() {
        return score;
    }

    @Override
    public void assertAvailable() {
        score.assertAvailable();
    }
}
