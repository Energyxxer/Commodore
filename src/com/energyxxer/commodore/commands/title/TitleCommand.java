package com.energyxxer.commodore.commands.title;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public abstract class TitleCommand implements Command {
    protected Entity player;

    public TitleCommand(Entity player) {
        this.player = player;

        if(!player.isPlayer()) throw new IllegalArgumentException("Provided entity '" + player + "' includes non-player entities, expected only players");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
