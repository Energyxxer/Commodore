package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;

public class LootReplaceEntity implements LootCommand.LootDestination {
    private final Entity entity;
    private final Type slot;

    public LootReplaceEntity(Entity entity, Type slot) {
        this.entity = entity;
        this.slot = slot;
        assertSlot(slot);
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("replace entity \be0 " + slot, entity);
    }
}
