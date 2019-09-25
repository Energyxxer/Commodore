package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionEntity extends ExecuteCondition {
    @NotNull
    private final Entity entity;

    public ExecuteConditionEntity(@NotNull ConditionType flowController, @NotNull Entity entity) {
        super(flowController);
        this.entity = entity;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "entity " + entity);
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
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
