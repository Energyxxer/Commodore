package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

public class ExecutePositionedAtEntity implements ExecuteModifier {

    private Entity entity;

    public ExecutePositionedAtEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("positioned at " + entity.getSelectorAs(sender));
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
