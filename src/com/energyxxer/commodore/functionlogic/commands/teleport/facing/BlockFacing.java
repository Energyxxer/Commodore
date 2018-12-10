package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class BlockFacing implements TeleportFacing {
    @NotNull
    private final CoordinateSet pos;

    public BlockFacing(@NotNull CoordinateSet pos) {
        this.pos = pos;
    }

    @NotNull
    @Override
    public String getRaw() {
        return "facing " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS);
    }
}
