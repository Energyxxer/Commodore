package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class SetblockCommand implements Command {
    private CoordinateSet pos;
    private Block block;

    public SetblockCommand(CoordinateSet pos, Block block) {
        this.pos = pos;
        this.block = block;

        if(!block.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual blocks");
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setblock " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block);
    }
}
