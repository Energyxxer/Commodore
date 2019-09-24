package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ExecuteFacingEntity implements ExecuteModifier {
    @NotNull
    private final Entity entity;
    @NotNull
    private final EntityAnchor anchor;

    public ExecuteFacingEntity(@NotNull Entity entity) {
        this(entity, EntityAnchor.FEET);
    }

    public ExecuteFacingEntity(@NotNull Entity entity, @NotNull EntityAnchor anchor) {
        this.entity = entity;
        this.anchor = anchor;
    }

    @NotNull
    @Override
    public SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, "facing entity " + entity + " " + anchor.toString().toLowerCase(Locale.ENGLISH));
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

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        ExecutionVariableMap map = new ExecutionVariableMap(ExecutionVariable.YAW, ExecutionVariable.PITCH, ExecutionVariable.CONDITION);
        if(entity.getLimit() != 1) map.setUsed(ExecutionVariable.COUNT);
        return map;
    }

    @NotNull
    public Entity getEntity() {
        return entity;
    }

    @NotNull
    public EntityAnchor getAnchor() {
        return anchor;
    }
}
