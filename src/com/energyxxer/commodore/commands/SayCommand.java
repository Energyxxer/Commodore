package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class SayCommand implements Command {
    private String message;

    public SayCommand(String message) {
        this.message = message;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "say " + message;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "say " + message);
    }
}
