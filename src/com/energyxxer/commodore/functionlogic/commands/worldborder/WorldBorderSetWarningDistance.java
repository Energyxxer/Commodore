package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetWarningDistance extends WorldBorderCommand {
    private final int distance;

    public WorldBorderSetWarningDistance(int distance) {
        this.distance = distance;

        if(distance < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Distance must not be less than 0, found " + distance, distance, "DISTANCE");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder warning distance " + distance);
    }
}
