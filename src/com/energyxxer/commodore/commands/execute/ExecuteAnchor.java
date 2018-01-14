package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

public class ExecuteAnchor implements ExecuteModifier {

    public EntityAnchor anchor;

    public ExecuteAnchor(EntityAnchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public SubCommandResult getSubCommand(Entity sender) {
        return new SubCommandResult("anchored " + anchor.toString().toLowerCase());
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
