package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
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
