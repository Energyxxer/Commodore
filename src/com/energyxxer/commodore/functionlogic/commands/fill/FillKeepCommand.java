package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

@Deprecated
public class FillKeepCommand extends FillCommand {
    public FillKeepCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        super(pos1, pos2, block, new FillKeepMode());
    }
}
