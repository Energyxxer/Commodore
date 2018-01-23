package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.PITCH;
import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.YAW;

public class ExecuteFacingEntity implements ExecuteModifier {
    private Entity entity;
    private EntityAnchor anchor;

    public ExecuteFacingEntity(Entity entity) {
        this(entity, EntityAnchor.FEET);
    }

    public ExecuteFacingEntity(Entity entity, EntityAnchor anchor) {
        this.entity = entity;
        this.anchor = anchor;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("facing entity " + entity.getSelectorAs(sender) + " " + anchor.toString().toLowerCase(), YAW, PITCH);
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
        return true;
    }

    @Override
    public ExecutionVariableMap getModifiedExecutionVariables() {
        return new ExecutionVariableMap(ExecutionVariable.YAW, ExecutionVariable.PITCH);
    }
}
