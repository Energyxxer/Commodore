package com.energyxxer.commodore.functionlogic.commands.teleport.destination;

import com.energyxxer.commodore.functionlogic.commands.teleport.TeleportClause;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;

public interface TeleportDestination extends TeleportClause {
    static TeleportDestination create(Entity destination) {
        return new EntityDestination(destination);
    }

    static TeleportDestination create(CoordinateSet destination) {
        return new BlockDestination(destination);
    }
}
