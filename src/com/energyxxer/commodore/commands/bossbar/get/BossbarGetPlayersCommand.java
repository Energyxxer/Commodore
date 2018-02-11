package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.types.BossbarReference;

public class BossbarGetPlayersCommand extends BossbarGetCommand {

    public BossbarGetPlayersCommand(BossbarReference reference) {
        super(reference);
    }

    @Override
    protected String getKeyword() {
        return "players";
    }
}
