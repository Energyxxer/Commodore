package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class SeedCommand implements Command {
    @Override
    public String getRawCommand(Entity sender) {
        return "seed";
    }
}
