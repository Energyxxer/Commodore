package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

public class TimeQueryCommand extends TimeCommand {
    public enum TimeCounter {
        DAY, DAYTIME, GAMETIME
    }

    private TimeCounter counter;

    public TimeQueryCommand(TimeCounter counter) {
        this.counter = counter;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "time query " + counter.toString().toLowerCase();
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "time query " + counter.toString().toLowerCase());
    }
}
