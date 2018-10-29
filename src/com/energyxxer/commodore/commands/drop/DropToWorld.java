package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class DropToWorld implements DropDestination {
    private final CoordinateSet pos;

    public DropToWorld(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("world " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS));
    }
}
