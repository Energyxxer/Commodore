package com.energyxxer.commodore.commands.setblock;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class SetblockCommand implements Command {
    private final CoordinateSet pos;
    private final Block block;

    public SetblockCommand(CoordinateSet pos, Block block) {
        this.pos = pos;
        this.block = block;

        assertStandalone(block.getBlockType());
    }

    public SetblockCommand(CoordinateSet pos, Type blockType) {
        this(pos, new Block(blockType));
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setblock " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block);
    }
}
