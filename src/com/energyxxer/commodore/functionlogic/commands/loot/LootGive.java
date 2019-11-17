package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class LootGive implements LootCommand.LootDestination {
    @NotNull
    private final Entity players;

    public LootGive(@NotNull Entity players) {
        this.players = players;

        players.assertPlayer();
        players.assertEntityFriendly();
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("give " + players);
    }

    @Override
    public void assertAvailable() {
        players.assertAvailable();
    }
}
