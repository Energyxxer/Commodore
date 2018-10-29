package com.energyxxer.commodore.functionlogic.commands.teleport.destination;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class BlockDestination implements TeleportDestination {
    private final CoordinateSet pos;

    public BlockDestination(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public String getRaw() {
        return pos.getAs(Coordinate.DisplayMode.ENTITY_POS);
    }
}
