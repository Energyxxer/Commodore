package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class DropFromFish implements DropSource {
    private final String lootTable;
    private final CoordinateSet waterLoc;
    private final ToolOrHand tool;

    public DropFromFish(String lootTable, CoordinateSet waterLoc) {
        this(lootTable, waterLoc, null);
    }

    public DropFromFish(String lootTable, CoordinateSet waterLoc, ToolOrHand tool) {
        this.lootTable = lootTable;
        this.waterLoc = waterLoc;
        this.tool = tool;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("fish " + lootTable + " " + waterLoc.getAs(Coordinate.DisplayMode.BLOCK_POS) + (tool != null ? " " + tool : ""));
    }
}
