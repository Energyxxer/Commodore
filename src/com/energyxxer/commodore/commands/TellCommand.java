package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TellCommand implements Command {
    private Entity player;
    private String message;

    public TellCommand(Entity player, String message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tell " + player.getSelectorAs(sender) + " " + message;
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
