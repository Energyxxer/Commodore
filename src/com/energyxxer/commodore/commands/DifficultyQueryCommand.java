package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class DifficultyQueryCommand extends DifficultyCommand {
    @Override
    public String getRawCommand(Entity sender) {
        return "difficulty";
    }
}
