package com.energyxxer.commodore.functionlogic.commands.teleport.destination;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class BlockDestination implements TeleportDestination {
    @NotNull
    private final CoordinateSet pos;

    public BlockDestination(@NotNull CoordinateSet pos) {
        this.pos = pos;
    }

    @NotNull
    @Override
    public String getRaw() {
        return pos.getAs(Coordinate.DisplayMode.ENTITY_POS);
    }
}
