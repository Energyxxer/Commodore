package com.energyxxer.commodore.functionlogic.commands.gamemode;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class GamemodeCommand implements Command {
    @NotNull
    private final Type gamemode;
    @Nullable
    private final Entity player;

    public GamemodeCommand(@NotNull Type gamemode) {
        this(gamemode, null);
    }

    public GamemodeCommand(@NotNull Type gamemode, @Nullable Entity player) {
        this.gamemode = gamemode;
        this.player = player;

        assertGamemode(gamemode);
        if(player != null) {
            player.assertPlayer();
            player.assertEntityFriendly();
        }
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return player != null ? new CommandResolution(execContext, "gamemode " + gamemode + " " + player) : new CommandResolution(execContext, "gamemode " + gamemode);
    }

    @Override
    public void assertAvailable() {
        if (player != null) player.assertAvailable();
    }
}
