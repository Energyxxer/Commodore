package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;

public class BossbarGetPlayersCommand extends BossbarGetCommand {

    public BossbarGetPlayersCommand(Type reference) {
        super(reference);
    }

    @Override
    protected String getKeyword() {
        return "players";
    }
}
