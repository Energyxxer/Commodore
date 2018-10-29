package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class BlockFacing implements TeleportFacing {
    private final CoordinateSet pos;

    public BlockFacing(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public String getRaw() {
        return "facing " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS);
    }
}
