package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetWarningTime extends WorldBorderCommand {
    private final int time;

    public WorldBorderSetWarningTime(int time) {
        this.time = time;

        if(time < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time must not be less than 0, found " + time, time, "TIME");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder warning time " + time);
    }
}
