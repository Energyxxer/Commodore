package com.energyxxer.commodore.functionlogic.commands.teleport.destination;

import com.energyxxer.commodore.functionlogic.commands.teleport.TeleportClause;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface TeleportDestination extends TeleportClause {
    @NotNull
    @Contract("_ -> new")
    static TeleportDestination create(Entity destination) {
        return new EntityDestination(destination);
    }

    @NotNull
    @Contract("_ -> new")
    static TeleportDestination create(CoordinateSet destination) {
        return new BlockDestination(destination);
    }
}
