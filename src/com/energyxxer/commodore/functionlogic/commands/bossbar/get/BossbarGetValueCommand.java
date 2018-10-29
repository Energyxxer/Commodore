package com.energyxxer.commodore.functionlogic.commands.bossbar.get;

import com.energyxxer.commodore.types.Type;

public class BossbarGetValueCommand extends BossbarGetCommand {

    public BossbarGetValueCommand(Type bossbar) {
        super(bossbar);
    }

    @Override
    protected String getKeyword() {
        return "value";
    }
}
