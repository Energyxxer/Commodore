package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class ExperienceSetCommand extends ExperienceCommand {

    private int amount;
    private Unit unit;

    public ExperienceSetCommand(Entity player, int amount, Unit unit) {
        super(player);
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "xp set " + player.getSelectorAs(sender) + " " + amount + " " + unit.toString().toLowerCase();
    }
}
