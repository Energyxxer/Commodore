package com.energyxxer.commodore.commands.defaultgamemode;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class DefaultGamemodeCommand implements Command {

    private final Type gamemode;

    public DefaultGamemodeCommand(Type gamemode) {
        assertGamemode(gamemode);
        this.gamemode = gamemode;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "defaultgamemode " + gamemode);
    }
}
