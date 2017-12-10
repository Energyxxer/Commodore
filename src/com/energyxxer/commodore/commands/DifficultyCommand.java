package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.Difficulty;

public class DifficultyCommand implements Command {

    private Difficulty difficulty;

    public DifficultyCommand(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "difficulty " + difficulty;
    }
}
