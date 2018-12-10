package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarGetMaxCommand extends BossbarGetCommand {
    public BossbarGetMaxCommand(Type bossbar) {
        super(bossbar);
    }

    @Override
    protected @NotNull String getKeyword() {
        return "max";
    }
}
