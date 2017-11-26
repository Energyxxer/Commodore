package com.energyxxer.mctech.commands;

import com.energyxxer.mctech.Command;
import com.energyxxer.mctech.entity.Entity;

public class KillCommand implements Command {

    private Entity entity;

    public KillCommand(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "kill " + entity.getSelectorAs(sender);
    }
}
