package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.GamemodeType;

public class GamemodeCommand implements Command {

    private GamemodeType gamemode;
    private Entity player;

    public GamemodeCommand(GamemodeType gamemode, Entity player) {
        this.gamemode = gamemode;
        this.player = player;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "gamemode " + gamemode + " " + player.getSelectorAs(sender);
    }
}
