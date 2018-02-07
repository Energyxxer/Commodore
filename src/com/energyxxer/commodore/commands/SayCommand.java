package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class SayCommand implements Command {
    private String message;

    public SayCommand(String message) {
        this.message = message;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "say " + message);
    }
}
