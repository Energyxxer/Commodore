package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDistance extends WorldBorderCommand {
    private final double distance;
    private final int time;

    public WorldBorderSetDistance(double distance) {
        this(distance, 0);
    }

    public WorldBorderSetDistance(double distance, int time) {
        this.distance = distance;
        this.time = time;

        if(distance < 1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "World border width must not be less than 1.0, found " + distance, distance, "DISTANCE");
        if(time < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time must not be less than 0, found " + time, time, "TIME");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder set " + CommandUtils.numberToPlainString(distance) + ((time != 0) ? " " + time : ""));
    }
}
