package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.types.defaults.BossbarReference;

public class BossbarGetVisibleCommand extends BossbarGetCommand {

    public BossbarGetVisibleCommand(BossbarReference reference) {
        super(reference);
    }

    @Override
    protected String getKeyword() {
        return "visible";
    }
}
