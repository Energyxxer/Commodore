package com.energyxxer.commodore.commands.time;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TimeAddCommand extends TimeCommand {
    private final int amount;

    public TimeAddCommand(int amount) {
        this.amount = amount;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time add " + amount);
    }
}
