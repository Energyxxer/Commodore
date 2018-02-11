package com.energyxxer.commodore.commands.teleport.facing;

import com.energyxxer.commodore.rotation.Rotation;

public class RotationFacing implements TeleportFacing {
    private final Rotation rotation;

    public RotationFacing(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public String getRaw() {
        return rotation.toString();
    }
}
