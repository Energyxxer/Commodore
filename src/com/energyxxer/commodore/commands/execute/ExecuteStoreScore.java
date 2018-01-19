package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.LocalScore;

public class ExecuteStoreScore extends ExecuteStore {
    private LocalScore score;

    public ExecuteStoreScore(LocalScore score) {
        this.score = score;
    }

    public ExecuteStoreScore(StoreValue storeValue, LocalScore score) {
        super(storeValue);
        this.score = score;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult(this.getStarter() + "score " + CommandUtils.getRawReference(score.getHolder(), sender) + " " + score.getObjective().getName());
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
