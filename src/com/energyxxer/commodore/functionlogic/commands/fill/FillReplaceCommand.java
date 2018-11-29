package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

@Deprecated
public class FillReplaceCommand extends FillCommand {
    public FillReplaceCommand(CoordinateSet pos1, CoordinateSet pos2, Block block, Block replacement) {
        super(pos1, pos2, block, new FillReplaceMode(replacement));
    }
}
