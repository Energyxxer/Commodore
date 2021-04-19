package com.energyxxer.commodore.functionlogic.commands.experience;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ExperienceCommand implements Command {

    public enum Unit {
        POINTS, LEVELS
    }

    @NotNull
    protected final Entity player;

    public ExperienceCommand(@Nullable Entity player) {
        if(player == null) player = new Selector(Selector.BaseSelector.SENDER);
        this.player = player;
        player.assertEntityFriendly();
        player.assertPlayer();
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
