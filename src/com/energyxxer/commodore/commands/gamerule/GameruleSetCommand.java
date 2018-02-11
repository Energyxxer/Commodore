package com.energyxxer.commodore.commands.gamerule;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class GameruleSetCommand extends GameruleCommand {

    private final String gamerule;
    private final String value;

    public GameruleSetCommand(String gamerule, Object value) {
        this.gamerule = gamerule;
        this.value = String.valueOf(value);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamerule " +gamerule + " " + value);
    }
}
