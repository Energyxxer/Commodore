package com.energyxxer.commodore.commands.time;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class TimeSetCommand extends TimeCommand {
    public enum TimeOfDay {
        DAY(1000), MIDNIGHT(18000), NIGHT(13000), NOON(6000);
        private int time;

        TimeOfDay(int time) {
            this.time = time;
        }
    }

    private int time;

    public TimeSetCommand(int time) {
        this.time = time;
    }

    public TimeSetCommand(TimeOfDay time) {
        this(time.time);
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time set" + time);
    }
}
