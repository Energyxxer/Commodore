package com.energyxxer.commodore.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;

public class BossbarGetMaxCommand extends BossbarGetCommand {

    public BossbarGetMaxCommand(Type bossbar) {
        super(bossbar);
    }

    @Override
    protected String getKeyword() {
        return "max";
    }
}
