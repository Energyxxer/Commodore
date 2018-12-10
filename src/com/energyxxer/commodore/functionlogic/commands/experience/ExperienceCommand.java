package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ExperienceCommand implements Command {

    public enum Unit {
        POINTS, LEVELS
    }

    @NotNull
    protected final Entity player;

    public ExperienceCommand(@NotNull Entity player) {
        this.player = player;
        player.assertPlayer();
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }

}
