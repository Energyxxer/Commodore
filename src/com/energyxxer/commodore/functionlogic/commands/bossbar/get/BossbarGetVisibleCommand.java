package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;

public class BossbarGetVisibleCommand extends BossbarGetCommand {

    public BossbarGetVisibleCommand(Type bossbar) {
        super(bossbar);
    }

    @Override
    protected String getKeyword() {
        return "visible";
    }
}
