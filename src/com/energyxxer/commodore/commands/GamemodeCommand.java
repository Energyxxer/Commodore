package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.types.GamemodeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

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

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "gamemode " + gamemode + " \be0", player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
