package com.energyxxer.commodore.commands.teleport.destination;

import com.energyxxer.commodore.commands.teleport.TeleportClause;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public interface TeleportDestination extends TeleportClause {
    static TeleportDestination create(Entity destination) {
        return new EntityDestination(destination);
    }

    static TeleportDestination create(CoordinateSet destination) {
        return new BlockDestination(destination);
    }
}
