package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.rotation.Rotation;
import org.jetbrains.annotations.NotNull;

public class RotationFacing implements TeleportFacing {
    @NotNull
    private final Rotation rotation;

    public RotationFacing(@NotNull Rotation rotation) {
        this.rotation = rotation;
    }

    @NotNull
    @Override
    public String getRaw() {
        return rotation.toString();
    }
}
