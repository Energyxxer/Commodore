package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class SetblockCommand implements Command {
    private CoordinateSet pos;
    private Block block;

    public SetblockCommand(CoordinateSet pos, Block block) {
        this.pos = pos;
        this.block = block;
    }

    @Override
    public String getRawCommand(Entity sender) {
        StringBuilder sb = new StringBuilder("setblock ");
        sb.append(pos);
        sb.append(' ');
        sb.append(block);
        return sb.toString();
    }
}
