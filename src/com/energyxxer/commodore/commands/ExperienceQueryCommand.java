package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class ExperienceQueryCommand extends ExperienceCommand {

    private Entity player;
    private Unit unit;

    public ExperienceQueryCommand(Entity player, Unit unit) {
        this.player = player;
        this.unit = unit;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "experience query " + player.getSelectorAs(sender) + " " + unit.toString().toLowerCase();
    }
}
