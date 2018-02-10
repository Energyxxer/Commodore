package com.energyxxer.commodore.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class FillOutlineCommand extends FillCommand {
    public FillOutlineCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        super(pos1, pos2, block);
    }

    @Override
    protected String getMaskExtra() {
        return " outline";
    }
}
