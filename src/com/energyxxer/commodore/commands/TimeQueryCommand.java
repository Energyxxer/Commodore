package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

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
}
