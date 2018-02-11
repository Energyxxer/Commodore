package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.types.BossbarReference;
import org.jetbrains.annotations.NotNull;

public class BossbarSetPlayersCommand extends BossbarSetCommand {
    private Entity players;

    public BossbarSetPlayersCommand(BossbarReference reference, Entity players) {
        super(reference);

        players.assertPlayer();

        this.players = players;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "players \be0", players);
    }
}
