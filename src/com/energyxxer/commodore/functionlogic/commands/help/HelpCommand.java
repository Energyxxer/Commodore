package com.energyxxer.commodore.functionlogic.commands.help;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class HelpCommand implements Command {

    private final String commandPath;

    public HelpCommand() {
        this(null);
    }

    public HelpCommand(String commandPath) {
        this.commandPath = commandPath;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "help" + ((commandPath != null) ? " " + commandPath : ""));
    }

    @Override
    public boolean isScoreboardManipulation() {
        return true;
    }
}
