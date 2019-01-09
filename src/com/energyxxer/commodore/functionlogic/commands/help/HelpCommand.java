package com.energyxxer.commodore.functionlogic.commands.help;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HelpCommand implements Command {

    @Nullable
    private final String commandPath;

    public HelpCommand() {
        this(null);
    }

    public HelpCommand(@Nullable String commandPath) {
        this.commandPath = commandPath;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "help" + ((commandPath != null) ? " " + commandPath : ""));
    }

}
