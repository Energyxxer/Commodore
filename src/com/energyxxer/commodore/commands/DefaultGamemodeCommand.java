package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
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

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "defaultgamemode " + gamemode);
    }
}
