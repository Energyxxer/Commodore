package com.energyxxer.mctech.commands;

import com.energyxxer.mctech.Command;
import com.energyxxer.mctech.block.Block;
import com.energyxxer.mctech.coordinates.CoordinateSet;
import com.energyxxer.mctech.entity.Entity;

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
