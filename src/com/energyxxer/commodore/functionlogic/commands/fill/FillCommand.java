package com.energyxxer.commodore.functionlogic.commands.fill;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class FillCommand implements Command {

    private final CoordinateSet pos1;
    private final CoordinateSet pos2;

    private final Block block;

    public FillCommand(CoordinateSet pos1, CoordinateSet pos2, Block block) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.block = block;
        assertStandalone(block.getBlockType());
    }

    protected String getMaskExtra() {
        return "";
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "fill " + pos1.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + pos2.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block + getMaskExtra());
    }
}
