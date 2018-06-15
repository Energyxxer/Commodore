package com.energyxxer.commodore.commands.defaultgamemode;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.defaults.GamemodeType;
import org.jetbrains.annotations.NotNull;

public class DefaultGamemodeCommand implements Command {

    private final GamemodeType gamemode;

    public DefaultGamemodeCommand(GamemodeType gamemode) {
        this.gamemode = gamemode;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "defaultgamemode " + gamemode);
    }
}
