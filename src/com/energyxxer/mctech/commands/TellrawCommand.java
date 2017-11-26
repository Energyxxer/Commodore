package com.energyxxer.mctech.commands;

import com.energyxxer.mctech.Command;
import com.energyxxer.mctech.entity.Entity;
import com.energyxxer.mctech.textcomponents.TextComponent;

public class TellrawCommand implements Command {
    private Entity player;
    private TextComponent message;

    public TellrawCommand(Entity player, TextComponent message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tellraw " + player.getSelectorAs(sender) + " " + message;
    }
}
