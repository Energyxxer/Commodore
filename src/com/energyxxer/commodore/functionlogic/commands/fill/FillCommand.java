package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.block.BlockFormatter;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class FillCommand implements Command {

    public interface FillMode {
        @NotNull
        String getMaskExtra();
    }

    @NotNull
    private final CoordinateSet pos1;
    @NotNull
    private final CoordinateSet pos2;

    @NotNull
    private final Block block;

    @NotNull
    private final FillMode mode;

    public FillCommand(@NotNull CoordinateSet pos1, @NotNull CoordinateSet pos2, @NotNull Block block) {
        this(pos1, pos2, block, new FillReplaceMode());
    }

    public FillCommand(@NotNull CoordinateSet pos1, @NotNull CoordinateSet pos2, @NotNull Block block, @NotNull FillMode mode) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.block = block;
        this.mode = mode;
        assertStandalone(block.getBlockType());
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "fill " + pos1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + pos2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + BlockFormatter.asSet(block) + mode.getMaskExtra());
    }
}
