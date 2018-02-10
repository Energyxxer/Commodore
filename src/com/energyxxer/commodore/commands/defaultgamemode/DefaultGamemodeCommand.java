package com.energyxxer.commodore.commands.defaultgamemode;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.GamemodeType;

public class DefaultGamemodeCommand implements Command {

    private GamemodeType gamemode;

    public DefaultGamemodeCommand(GamemodeType gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "defaultgamemode " + gamemode);
    }
}
