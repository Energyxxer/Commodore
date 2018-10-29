package com.energyxxer.commodore.functionlogic.commands.time;

import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.TimeSpan;
import org.jetbrains.annotations.NotNull;

public class TimeAddCommand extends TimeCommand {
    private final TimeSpan time;

    public TimeAddCommand(TimeSpan time) {
        this.time = time;
    }
    public TimeAddCommand(int time) {
        this(new TimeSpan(time));
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time add " + time);
    }
}
