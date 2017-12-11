package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;

public class TellCommand implements Command {
    private Entity player;
    private String message;

    public TellCommand(Entity player, String message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tell " + player.getSelectorAs(sender) + " " + message;
    }
}
