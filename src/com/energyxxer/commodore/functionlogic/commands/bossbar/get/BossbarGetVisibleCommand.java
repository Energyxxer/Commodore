package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class BossbarGetVisibleCommand extends BossbarGetCommand {

    public BossbarGetVisibleCommand(Type bossbar) {
        super(bossbar);
    }

    @Override
    protected @NotNull String getKeyword() {
        return "visible";
    }
}
