package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.entity.Entity;

public class DropToPlayer implements DropDestination {
    private final Entity players;

    public DropToPlayer(Entity players) {
        this.players = players;

        players.assertPlayer();
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("player \be0", players);
    }
}
