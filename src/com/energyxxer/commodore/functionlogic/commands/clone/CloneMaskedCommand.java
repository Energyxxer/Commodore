package com.energyxxer.commodore.functionlogic.commands.clone;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class CloneMaskedCommand extends CloneCommand {
    public CloneMaskedCommand(@NotNull CoordinateSet source1, @NotNull CoordinateSet source2, @NotNull CoordinateSet destination) {
        super(source1, source2, destination);
    }

    public CloneMaskedCommand(@NotNull CoordinateSet source1, @NotNull CoordinateSet source2, @NotNull CoordinateSet destination, @NotNull SourceMode sourceMode) {
        super(source1, source2, destination, sourceMode);
    }

    @NotNull
    @Override
    protected String getMaskExtra() {
        return " masked";
    }
}
