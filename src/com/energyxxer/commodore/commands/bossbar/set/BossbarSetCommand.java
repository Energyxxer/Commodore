package com.energyxxer.commodore.commands.bossbar.set;

import com.energyxxer.commodore.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.types.Type;

public abstract class BossbarSetCommand extends BossbarCommand {
    protected final Type bossbar;

    public BossbarSetCommand(Type bossbar) {
        this.bossbar = bossbar;
    }

    protected String getBase() {
        return "bossbar set " + bossbar + " ";
    }
}
