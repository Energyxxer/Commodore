package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarSetPlayersCommand extends BossbarSetCommand {
    @NotNull
    private final Entity players;

    public BossbarSetPlayersCommand(@NotNull Type bossbar, @NotNull Entity players) {
        super(bossbar);

        players.assertPlayer();

        this.players = players;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, getBase() + "players \be0", players);
    }
}
