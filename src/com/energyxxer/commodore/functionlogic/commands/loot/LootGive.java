package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;

public class LootGive implements LootCommand.LootDestination {
    private final Entity players;

    public LootGive(Entity players) {
        this.players = players;

        players.assertPlayer();
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("give \be0", players);
    }
}
