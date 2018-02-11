package com.energyxxer.commodore.commands.datapack;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class DataPackDisableCommand extends DataPackCommand {

    private final String pack;

    public DataPackDisableCommand(String pack) {
        this.pack = pack;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "datapack disable " + pack);
    }
}
