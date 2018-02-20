package com.energyxxer.commodore.commands.seed;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
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
