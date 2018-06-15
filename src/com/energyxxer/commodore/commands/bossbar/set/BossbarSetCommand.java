package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.types.defaults.BossbarReference;

public abstract class BossbarSetCommand extends BossbarCommand {
    protected final BossbarReference reference;

    public BossbarSetCommand(BossbarReference reference) {
        this.reference = reference;
    }

    protected String getBase() {
        return "bossbar set " + reference + " ";
    }
}
