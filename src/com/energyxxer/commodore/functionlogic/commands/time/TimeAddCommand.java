package com.energyxxer.commodore.functionlogic.commands.time;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.TimeSpan;
import org.jetbrains.annotations.NotNull;

public class TimeAddCommand extends TimeCommand {
    @NotNull
    private final TimeSpan time;

    public TimeAddCommand(@NotNull TimeSpan time) {
        this.time = time;

        if(time.amount < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time must be non-negative, found " + time, time, "TIME");
    }
    public TimeAddCommand(int time) {
        this(new TimeSpan(time));
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time add " + time);
    }
}
