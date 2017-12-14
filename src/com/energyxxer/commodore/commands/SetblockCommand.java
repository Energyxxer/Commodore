package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class SetblockCommand implements Command {
    private CoordinateSet pos;
    private Block block;

    public SetblockCommand(CoordinateSet pos, Block block) {
        this.pos = pos;
        this.block = block;

        if (!block.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual blocks");
    }

    @Override
    public String getRawCommand(Entity sender) {
        StringBuilder sb = new StringBuilder("setblock ");
        sb.append(pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
        sb.append(' ');
        sb.append(block);
        return sb.toString();
    }
}
