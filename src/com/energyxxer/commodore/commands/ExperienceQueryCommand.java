package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class ExperienceQueryCommand extends ExperienceCommand {

    private Unit unit;

    public ExperienceQueryCommand(Entity player, Unit unit) {
        super(player);
        this.unit = unit;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "xp query " + player.getSelectorAs(sender) + " " + unit.toString().toLowerCase();
    }
}
