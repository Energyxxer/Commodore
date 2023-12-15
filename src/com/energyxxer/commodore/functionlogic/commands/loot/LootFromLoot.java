package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import org.jetbrains.annotations.NotNull;

public class LootFromLoot implements LootCommand.LootSource {
    private final String lootTable;
    private final ToolOrHand tool;

    public LootFromLoot(String lootTable) {
        this(lootTable, null);
    }

    public LootFromLoot(String lootTable, ToolOrHand tool) {
        this.lootTable = lootTable;
        this.tool = tool;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("loot " + lootTable + (tool != null ? " " + tool : ""));
    }
}
