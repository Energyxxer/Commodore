package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;

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
