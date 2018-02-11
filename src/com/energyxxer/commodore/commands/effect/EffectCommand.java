package com.energyxxer.commodore.commands.effect;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public abstract class EffectCommand implements Command {

    protected final Entity entity;

    public EffectCommand(Entity entity) {
        this.entity = entity;
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
