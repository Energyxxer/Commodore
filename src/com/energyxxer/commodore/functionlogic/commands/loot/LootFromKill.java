package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class LootFromKill implements LootCommand.LootSource {
    private final Entity entity;
    private final ToolOrHand tool;

    public LootFromKill(Entity entity) {
        this(entity, null);
    }

    public LootFromKill(Entity entity, ToolOrHand tool) {
        this.entity = entity;
        this.tool = tool;
        entity.assertSingle();
        entity.assertEntityFriendly();
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("kill " + entity + (tool != null ? " " + tool : ""));
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
