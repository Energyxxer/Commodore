package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class LootInsertBlock implements LootCommand.LootDestination {
    private final CoordinateSet pos;

    public LootInsertBlock(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("insert " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
    }
}
