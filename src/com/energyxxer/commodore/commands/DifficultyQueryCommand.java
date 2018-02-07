package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class DifficultyQueryCommand extends DifficultyCommand {

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "difficulty");
    }
}
