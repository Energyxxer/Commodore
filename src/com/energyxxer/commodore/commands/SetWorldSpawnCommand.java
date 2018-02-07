package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class SetWorldSpawnCommand implements Command {

    private CoordinateSet pos;

    public SetWorldSpawnCommand(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setworldspawn " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
    }
}
