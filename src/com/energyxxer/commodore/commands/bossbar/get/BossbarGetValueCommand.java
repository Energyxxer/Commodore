package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.types.BossbarReference;

public class BossbarGetValueCommand extends BossbarGetCommand {

    public BossbarGetValueCommand(BossbarReference reference) {
        super(reference);
    }

    @Override
    protected String getKeyword() {
        return "value";
    }
}
