package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionDataEntity extends ExecuteCondition {
    @NotNull
    private final Entity entity;
    @NotNull
    private final NBTPath path;

    public ExecuteConditionDataEntity(@NotNull ConditionType flowController, @NotNull Entity entity, @NotNull NBTPath path) {
        super(flowController);
        this.entity = entity;
        this.path = path;

        entity.assertSingle();
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "data entity " + entity + " " + path);
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

    @NotNull
    public NBTPath getPath() {
        return path;
    }
}
