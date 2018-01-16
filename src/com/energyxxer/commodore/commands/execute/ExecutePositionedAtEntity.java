package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

import static com.energyxxer.commodore.commands.execute.SubCommandResult.ExecutionChange.*;

public class ExecutePositionedAtEntity implements ExecuteModifier {

    private Entity entity;

    public ExecutePositionedAtEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("positioned at " + entity.getSelectorAs(sender), X, Y, Z);
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
}
