package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class LootSpawn implements LootCommand.LootDestination {
    @NotNull
    private final CoordinateSet pos;

    public LootSpawn(@NotNull CoordinateSet pos) {
        this.pos = pos;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("spawn " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS));
    }
}
