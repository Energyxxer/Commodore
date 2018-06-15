package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.types.defaults.BossbarReference;

public class BossbarGetMaxCommand extends BossbarGetCommand {

    public BossbarGetMaxCommand(BossbarReference reference) {
        super(reference);
    }

    @Override
    protected String getKeyword() {
        return "max";
    }
}
