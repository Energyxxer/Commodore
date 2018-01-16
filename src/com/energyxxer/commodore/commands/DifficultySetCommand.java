package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.DifficultyType;

public class DifficultySetCommand extends DifficultyCommand {

    private DifficultyType difficulty;

    public DifficultySetCommand(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "difficulty " + difficulty;
    }
}
