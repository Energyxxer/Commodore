package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.coordinates.CoordinateSet;

public class CloneFilteredCommand extends CloneCommand {

    private Block block;

    public CloneFilteredCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, Block block) {
        super(source1, source2, destination);
        this.block = block;
    }

    public CloneFilteredCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, Block block, SourceMode sourceMode) {
        super(source1, source2, destination, sourceMode);
        this.block = block;
    }

    protected String getMaskExtra() {
        return " filtered " + block;
    }
}
