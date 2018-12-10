package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootFromFish implements LootCommand.LootSource {
    @NotNull
    private final String lootTable;
    @NotNull
    private final CoordinateSet waterLoc;
    @Nullable
    private final ToolOrHand tool;

    public LootFromFish(@NotNull String lootTable, @NotNull CoordinateSet waterLoc) {
        this(lootTable, waterLoc, null);
    }

    public LootFromFish(@NotNull String lootTable, @NotNull CoordinateSet waterLoc, @Nullable ToolOrHand tool) {
        this.lootTable = lootTable;
        this.waterLoc = waterLoc;
        this.tool = tool;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("fish " + lootTable + " " + waterLoc.getAs(Coordinate.DisplayMode.BLOCK_POS) + (tool != null ? " " + tool : ""));
    }
}
