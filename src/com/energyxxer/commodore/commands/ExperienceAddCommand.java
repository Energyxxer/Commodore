package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class ExperienceAddCommand extends ExperienceCommand {

    private int amount;
    private Unit unit;

    public ExperienceAddCommand(Entity player, int amount, Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "xp add " + player.getSelectorAs(sender) + " " + amount + " " + unit.toString().toLowerCase();
    }
}
