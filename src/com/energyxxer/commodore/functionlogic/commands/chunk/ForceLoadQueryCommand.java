package com.energyxxer.commodore.functionlogic.commands.chunk;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForceLoadQueryCommand extends ForceLoadCommand {
    @Nullable
    private CoordinateSet column;

    public ForceLoadQueryCommand() {
        this(null);
    }

    public ForceLoadQueryCommand(@Nullable CoordinateSet column) {
        this.column = column;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "forceload query" + (column != null ? " " + column.getXZAs(Coordinate.DisplayMode.BLOCK_POS) : ""));
    }
}
