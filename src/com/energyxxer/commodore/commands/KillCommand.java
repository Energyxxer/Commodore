package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class KillCommand implements Command {

    private Entity entity;

    public KillCommand(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "kill " + entity.getSelectorAs(sender);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
