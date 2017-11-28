package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;

public class SubCommandResult {
    private String subCommand;
    private Entity newSender;

    public SubCommandResult(String subCommand) {
        this.subCommand = subCommand;
    }

    public SubCommandResult(String subCommand, Entity newSender) {
        this.subCommand = subCommand;
        this.newSender = newSender;
    }

    public String getSubCommand() {
        return subCommand;
    }

    public Entity getNewSender() {
        return newSender;
    }
}
