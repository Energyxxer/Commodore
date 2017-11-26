package com.energyxxer.mctech.coordinates;

import com.energyxxer.mctech.entity.Entity;
import com.energyxxer.mctech.commands.execute.ExecuteModifier;
import com.energyxxer.mctech.commands.execute.SubCommandResult;

public class ExecuteAtEntity implements ExecuteModifier {

    private Entity entity;

    public ExecuteAtEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("at " + entity.getSelectorAs(sender));
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
