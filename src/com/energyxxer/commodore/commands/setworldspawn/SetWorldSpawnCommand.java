package com.energyxxer.commodore.commands.setworldspawn;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SetWorldSpawnCommand implements Command {

    private final CoordinateSet pos;

    public SetWorldSpawnCommand(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setworldspawn " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
    }
}
