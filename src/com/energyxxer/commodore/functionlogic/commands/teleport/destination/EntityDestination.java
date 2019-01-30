package com.energyxxer.commodore.functionlogic.commands.teleport.destination;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class EntityDestination implements TeleportDestination {
    @NotNull
    private final Entity entity;

    public EntityDestination(@NotNull Entity entity) {
        this.entity = entity;
        entity.assertSingle();
    }

    @NotNull
    @Override
    public String getRaw() {
        return entity.toString();
    }

}
