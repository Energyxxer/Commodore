package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class DropFromMine implements DropSource {
    private final CoordinateSet pos;
    private final ToolOrHand tool;

    public DropFromMine(CoordinateSet pos) {
        this(pos, null);
    }

    public DropFromMine(CoordinateSet pos, ToolOrHand tool) {
        this.pos = pos;
        this.tool = tool;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("mine " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + (tool != null ? " " + tool : ""));
    }
}
