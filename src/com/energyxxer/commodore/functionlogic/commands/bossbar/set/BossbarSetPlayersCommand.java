package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BossbarSetPlayersCommand extends BossbarSetCommand {
    @Nullable
    private final Entity players;

    public BossbarSetPlayersCommand(@NotNull Type bossbar) {
        this(bossbar, null);
    }

    public BossbarSetPlayersCommand(@NotNull Type bossbar, @Nullable Entity players) {
        super(bossbar);

        if (players != null) {
            players.assertEntityFriendly();
            players.assertPlayer();
        }

        this.players = players;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return players != null ? new CommandResolution(execContext, getBase() + "players " + players)
                : new CommandResolution(execContext, getBase() + "players");
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        if(players != null) players.assertAvailable();
    }
}
