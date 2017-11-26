package com.energyxxer.mctech.commands.execute;

import com.energyxxer.mctech.entity.Entity;

public class ExecuteAsEntity implements ExecuteModifier {
    private Entity entity;

    public ExecuteAsEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("as " + entity.getSelectorAs(sender), entity);
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
