package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class ExperienceCommand implements Command {

    public enum Unit {
        POINTS, LEVELS
    }

    @NotNull
    protected final Entity player;

    public ExperienceCommand(@NotNull Entity player) {
        this.player = player;
        player.assertEntityFriendly();
        player.assertPlayer();
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
