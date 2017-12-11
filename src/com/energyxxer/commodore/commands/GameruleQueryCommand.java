package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

public class GameruleQueryCommand extends GameruleCommand {

    private String gamerule;

    public GameruleQueryCommand(String gamerule) {
        this.gamerule = gamerule;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "gamerule " + gamerule;
    }
}
