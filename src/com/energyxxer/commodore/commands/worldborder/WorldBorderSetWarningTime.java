package com.energyxxer.commodore.commands.worldborder;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetWarningTime extends WorldBorderCommand {
    private final int time;

    public WorldBorderSetWarningTime(int time) {
        this.time = time;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder warning time " + time);
    }
}
