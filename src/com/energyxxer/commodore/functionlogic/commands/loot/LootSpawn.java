package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class LootSpawn implements LootCommand.LootDestination {
    private final CoordinateSet pos;

    public LootSpawn(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("spawn " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS));
    }
}
