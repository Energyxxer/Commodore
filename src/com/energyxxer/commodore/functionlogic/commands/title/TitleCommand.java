package com.energyxxer.commodore.functionlogic.commands.title;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class TitleCommand implements Command {
    @NotNull
    protected final Entity player;

    public TitleCommand(@NotNull Entity player) {
        this.player = player;

        player.assertPlayer();
    }

    @Override
    public void assertAvailable() {
        player.assertAvailable();
    }
}
