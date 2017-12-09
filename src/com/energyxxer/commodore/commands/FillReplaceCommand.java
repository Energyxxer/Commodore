package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class FillReplaceCommand extends FillCommand {

    private Block replace;

    public FillReplaceCommand(CoordinateSet pos1, CoordinateSet pos2, Block block, Block replace) {
        super(pos1, pos2, block);

        this.replace = replace;
    }

    @Override
    protected String getMaskExtra() {
        return " replace " + replace;
    }
}
