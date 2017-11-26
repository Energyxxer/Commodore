package com.energyxxer.mctech.commands;

import com.energyxxer.mctech.Command;
import com.energyxxer.mctech.entity.Entity;

public class SayCommand implements Command {
    private String message;

    public SayCommand(String message) {
        this.message = message;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "say " + message;
    }
}
