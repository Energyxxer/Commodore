package com.energyxxer.commodore.functionlogic.commands.setblock;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class SetblockCommand implements Command {
    @NotNull
    private final CoordinateSet pos;
    @NotNull
    private final Block block;
    @NotNull
    private final OldBlockHandlingMode mode;

    public enum OldBlockHandlingMode {
        REPLACE, KEEP, DESTROY;
        public static final OldBlockHandlingMode DEFAULT = REPLACE;
    }

    public SetblockCommand(@NotNull CoordinateSet pos, @NotNull Block block) {
        this(pos, block, OldBlockHandlingMode.REPLACE);
    }

    public SetblockCommand(@NotNull CoordinateSet pos, @NotNull Block block, @NotNull OldBlockHandlingMode mode) {
        this.pos = pos;
        this.block = block;
        this.mode = mode;

        assertStandalone(block.getBlockType());
    }

    public SetblockCommand(@NotNull CoordinateSet pos, @NotNull Type blockType) {
        this(pos, blockType, OldBlockHandlingMode.REPLACE);
    }

    public SetblockCommand(@NotNull CoordinateSet pos, @NotNull Type blockType, @NotNull OldBlockHandlingMode mode) {
        this(pos, new Block(blockType), mode);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setblock " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + block + (mode != OldBlockHandlingMode.DEFAULT ? " " + mode.toString().toLowerCase() : ""));
    }
}
