package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class TimeAddCommand extends TimeCommand {
    private int amount;

    public TimeAddCommand(int amount) {
        this.amount = amount;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "time add " + amount;
    }
}
