package com.energyxxer.commodore.functionlogic.commands;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class RawCommand implements Command {
    @NotNull
    private String command;

    public RawCommand(@NotNull String command) {
        this.command = command;
    }

    @NotNull
    public String getCommand() {
        return command;
    }

    public void setCommand(@NotNull String command) {
        this.command = command;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, command);
    }
}
