package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.Difficulty;

public class DifficultySetCommand extends DifficultyCommand {

    private Difficulty difficulty;

    public DifficultySetCommand(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "difficulty " + difficulty;
    }
}
