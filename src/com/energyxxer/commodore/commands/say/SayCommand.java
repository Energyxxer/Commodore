package com.energyxxer.commodore.commands.say;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class SayCommand implements Command {
    private final String message;

    public SayCommand(String message) {
        this.message = message;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "say " + message);
    }
}
