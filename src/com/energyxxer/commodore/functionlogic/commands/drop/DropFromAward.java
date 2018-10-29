package com.energyxxer.commodore.functionlogic.commands.drop;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;

public class DropFromAward implements DropSource {
    private final String lootTable;

    public DropFromAward(String lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("award " + lootTable);
    }
}
