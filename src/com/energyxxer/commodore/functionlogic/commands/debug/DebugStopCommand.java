package com.energyxxer.commodore.functionlogic.commands.debug;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DebugStopCommand extends DebugCommand {
    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "debug stop");
    }
}
