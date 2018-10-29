package com.energyxxer.commodore.functionlogic.commands.clone;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class CloneMaskedCommand extends CloneCommand {
    public CloneMaskedCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination) {
        super(source1, source2, destination);
    }

    public CloneMaskedCommand(CoordinateSet source1, CoordinateSet source2, CoordinateSet destination, SourceMode sourceMode) {
        super(source1, source2, destination, sourceMode);
    }

    @Override
    protected String getMaskExtra() {
        return " masked";
    }
}
