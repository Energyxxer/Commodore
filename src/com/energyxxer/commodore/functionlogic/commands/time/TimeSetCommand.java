package com.energyxxer.commodore.functionlogic.commands.time;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.TimeSpan;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class TimeSetCommand extends TimeCommand {
    public enum TimeOfDay {
        DAY(1000), MIDNIGHT(18000), NIGHT(13000), NOON(6000);
        private final int time;

        TimeOfDay(int time) {
            this.time = time;
        }
    }

    @NotNull
    private final String time;

    public TimeSetCommand(int time) {
        this(new TimeSpan(time));
    }

    @NotNull
    public TimeSetCommand(@NotNull TimeSpan time) {
        this.time = time.toString();
        if(time.amount < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Time must be non-negative, found " + time, time, "TIME");
    }

    @NotNull
    public TimeSetCommand(@NotNull TimeOfDay time) {
        this.time = time.toString().toLowerCase(Locale.ENGLISH);
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time set " + time);
    }
}
