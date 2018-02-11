package com.energyxxer.commodore.commands.worldborder;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetWarningDistance extends WorldBorderCommand {
    private final int distance;

    public WorldBorderSetWarningDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder warning distance " + distance);
    }
}
