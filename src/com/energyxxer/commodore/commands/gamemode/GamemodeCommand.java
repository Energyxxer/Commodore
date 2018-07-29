package com.energyxxer.commodore.commands.gamemode;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class GamemodeCommand implements Command {

    private final Type gamemode;
    private final Entity player;

    public GamemodeCommand(Type gamemode, Entity player) {
        this.gamemode = gamemode;
        this.player = player;

        assertGamemode(gamemode);
        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamemode " + gamemode + " \be0", player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
