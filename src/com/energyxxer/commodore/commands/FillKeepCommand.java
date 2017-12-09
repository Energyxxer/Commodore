package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class FillKeepCommand extends FillCommand {
    public FillKeepCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        super(pos1, pos2, block);
    }

    @Override
    protected String getMaskExtra() {
        return " keep";
    }
}
