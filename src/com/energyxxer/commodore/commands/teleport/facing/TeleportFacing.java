package com.energyxxer.commodore.commands.teleport.facing;

import com.energyxxer.commodore.commands.teleport.TeleportClause;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.rotation.Rotation;

public interface TeleportFacing extends TeleportClause {
    static TeleportFacing create(Rotation facing) {
        return new RotationFacing(facing);
    }

    static TeleportFacing create(Entity facing) {
        return new EntityFacing(facing);
    }

    static TeleportFacing create(CoordinateSet facing) {
        return new BlockFacing(facing);
    }
}
