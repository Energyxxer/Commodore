package com.energyxxer.commodore.commands.time;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class TimeQueryCommand extends TimeCommand {
    public enum TimeCounter {
        DAY, DAYTIME, GAMETIME
    }

    private final TimeCounter counter;

    public TimeQueryCommand(TimeCounter counter) {
        this.counter = counter;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time query " + counter.toString().toLowerCase());
    }
}
