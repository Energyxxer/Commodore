package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.types.BlockType;

public class CloneFilteredCommand extends CloneCommand {

    private BlockType block;

    public CloneFilteredCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, BlockType block) {
        super(source1, source2, destination);
        this.block = block;
    }

    public CloneFilteredCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, BlockType block, SourceMode sourceMode) {
        super(source1, source2, destination, sourceMode);
        this.block = block;
    }

    protected String getMaskExtra() {
        return " filtered " + block;
    }
}
