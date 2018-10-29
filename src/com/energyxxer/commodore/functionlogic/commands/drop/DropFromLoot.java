package com.energyxxer.commodore.functionlogic.commands.drop;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;

public class DropFromLoot implements DropSource {
    private final String lootTable;

    public DropFromLoot(String lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("loot " + lootTable);
    }
}
