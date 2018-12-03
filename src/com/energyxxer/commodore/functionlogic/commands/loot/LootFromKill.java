package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;

public class LootFromKill implements LootCommand.LootSource {
    private final Entity entity;

    public LootFromKill(Entity entity) {
        this.entity = entity;
        entity.assertSingle();
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("kill \be#", entity);
    }
}
