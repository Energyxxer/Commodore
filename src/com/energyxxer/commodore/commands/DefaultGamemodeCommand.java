package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.Gamemode;

public class DefaultGamemodeCommand implements Command {

    private Gamemode gamemode;

    public DefaultGamemodeCommand(Gamemode gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "defaultgamemode " + gamemode;
    }
}
