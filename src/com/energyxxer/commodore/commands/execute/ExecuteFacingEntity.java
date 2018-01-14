package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

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
        return new SubCommandResult("facing entity " + entity.getSelectorAs(sender) + " " + anchor.toString().toLowerCase());
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
