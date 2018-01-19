package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.textcomponents.TextComponent;

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
