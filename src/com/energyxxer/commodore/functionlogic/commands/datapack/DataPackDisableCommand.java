package com.energyxxer.commodore.functionlogic.commands.datapack;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DataPackDisableCommand extends DataPackCommand {
    @NotNull
    private final String pack;

    public DataPackDisableCommand(@NotNull String pack) {
        this.pack = pack;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack disable " + pack);
    }
}
