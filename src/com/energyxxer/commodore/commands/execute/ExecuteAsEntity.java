package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class ExecuteAsEntity implements ExecuteModifier {
    private final Entity entity;

    public ExecuteAsEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "as \be0", entity);
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

    @Override
    public Entity getNewSender() {
        return entity;
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap(ExecutionVariable.SENDER, ExecutionVariable.CONDITION);
        if(entity.getLimit() != 1) map.setUsed(ExecutionVariable.COUNT);
        return map;
    }
}
