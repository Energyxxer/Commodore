package com.energyxxer.commodore.commands.worldborder;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetCenter extends WorldBorderCommand {
    private final CoordinateSet pos;

    public WorldBorderSetCenter(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder center " + pos.getXZAs(Coordinate.DisplayMode.ENTITY_POS));
    }
}
