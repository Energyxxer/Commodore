package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderGetWidth extends WorldBorderCommand {

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder get");
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
