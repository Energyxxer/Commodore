package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class TeleportToEntityCommand extends TeleportCommand {

    private Entity entity;
    private Entity destination;

    public TeleportToEntityCommand(Entity entity, Entity destination) {
        this.entity = entity;
        this.destination = destination;

        if(destination.getLimit() < 0 || destination.getLimit() > 1) throw new IllegalArgumentException("Only one entity is allowed as destination, but passed entity (" + destination + ") allows for more than one.");
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tp " + entity.getSelectorAs(sender) + " " + destination.getSelectorAs(sender);
    }
}
