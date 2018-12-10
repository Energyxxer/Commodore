package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarGetPlayersCommand extends BossbarGetCommand {

    public BossbarGetPlayersCommand(Type reference) {
        super(reference);
    }

    @Override
    protected @NotNull String getKeyword() {
        return "players";
    }
}
