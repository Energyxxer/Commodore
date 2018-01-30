package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class DifficultyQueryCommand extends DifficultyCommand {
    @Override
    public String getRawCommand(Entity sender) {
        return "difficulty";
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "difficulty");
    }
}
