package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class LootFromMine implements LootCommand.LootSource {
    private final CoordinateSet pos;
    private final ToolOrHand tool;

    public LootFromMine(CoordinateSet pos) {
        this(pos, null);
    }

    public LootFromMine(CoordinateSet pos, ToolOrHand tool) {
        this.pos = pos;
        this.tool = tool;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("mine " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + (tool != null ? " " + tool : ""));
    }
}
