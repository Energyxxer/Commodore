package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;

public class LootFromLoot implements LootCommand.LootSource {
    private final String lootTable;

    public LootFromLoot(String lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("loot " + lootTable);
    }
}
