package com.energyxxer.commodore.functionlogic.commands.bossbar.set;

import com.energyxxer.commodore.functionlogic.commands.bossbar.BossbarCommand;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public abstract class BossbarSetCommand extends BossbarCommand {@NotNull
protected final Type bossbar;

    public BossbarSetCommand(@NotNull Type bossbar) {
        this.bossbar = bossbar;
    }

    protected @NotNull String getBase() {
        return "bossbar set " + bossbar + " ";
    }
}
