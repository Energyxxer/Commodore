package com.energyxxer.commodore.functionlogic.commands.defaultgamemode;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
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
