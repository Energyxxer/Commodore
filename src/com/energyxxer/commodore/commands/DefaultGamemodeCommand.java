package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.GamemodeType;

public class DefaultGamemodeCommand implements Command {

    private GamemodeType gamemode;

    public DefaultGamemodeCommand(GamemodeType gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "defaultgamemode " + gamemode;
    }
}
