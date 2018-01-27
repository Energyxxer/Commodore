package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ExecuteConditionEntity extends ExecuteCondition {
    public Entity entity;

    public ExecuteConditionEntity(ConditionType flowController, Entity entity) {
        super(flowController);
        this.entity = entity;
    }

    @Override
    public String getSubCommand(Entity sender) {
        return this.getStarter() + "entity " + entity.getSelectorAs(sender);
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity.getScoreboardAccesses();
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
