package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.commands.teleport.TeleportClause;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.rotation.Rotation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface TeleportFacing extends TeleportClause {
    @NotNull
    @Contract("_ -> new")
    static TeleportFacing create(Rotation facing) {
        return new RotationFacing(facing);
    }

    @NotNull
    @Contract("_ -> new")
    static TeleportFacing create(Entity facing) {
        return new EntityFacing(facing);
    }

    @NotNull
    @Contract("_ -> new")
    static TeleportFacing create(CoordinateSet facing) {
        return new BlockFacing(facing);
    }
}
