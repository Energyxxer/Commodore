package com.energyxxer.commodore.functionlogic.commands.clone;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.block.BlockFormatter;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class CloneFilteredCommand extends CloneCommand {

    @NotNull
    private final Block block;

    public CloneFilteredCommand(@NotNull CoordinateSet source1, @NotNull CoordinateSet source2, @NotNull CoordinateSet destination, @NotNull Block block) {
        super(source1, source2, destination);
        this.block = block;
    }

    public CloneFilteredCommand(@NotNull CoordinateSet source1, @NotNull CoordinateSet source2, @NotNull CoordinateSet destination, @NotNull Block block, @NotNull SourceMode sourceMode) {
        super(source1, source2, destination, sourceMode);
        this.block = block;
    }

    @NotNull
    protected String getMaskExtra() {
        return " filtered " + BlockFormatter.asMatch(block);
    }

    @Override
    public void assertAvailable() {
        block.assertAvailable();
    }
}
