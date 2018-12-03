package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class LootFromFish implements LootCommand.LootSource {
    private final String lootTable;
    private final CoordinateSet waterLoc;
    private final ToolOrHand tool;

    public LootFromFish(String lootTable, CoordinateSet waterLoc) {
        this(lootTable, waterLoc, null);
    }

    public LootFromFish(String lootTable, CoordinateSet waterLoc, ToolOrHand tool) {
        this.lootTable = lootTable;
        this.waterLoc = waterLoc;
        this.tool = tool;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("fish " + lootTable + " " + waterLoc.getAs(Coordinate.DisplayMode.BLOCK_POS) + (tool != null ? " " + tool : ""));
    }
}
