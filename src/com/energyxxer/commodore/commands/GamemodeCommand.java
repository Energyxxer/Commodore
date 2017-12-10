package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.Gamemode;

public class GamemodeCommand implements Command {

    private Gamemode gamemode;
    private Entity player;

    public GamemodeCommand(Gamemode gamemode, Entity player) {
        this.gamemode = gamemode;
        this.player = player;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "gamemode " + gamemode + " " + player.getSelectorAs(sender);
    }
}
