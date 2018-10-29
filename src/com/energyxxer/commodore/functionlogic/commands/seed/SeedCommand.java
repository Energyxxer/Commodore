package com.energyxxer.commodore.functionlogic.commands.seed;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SeedCommand implements Command {

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "seed");
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
