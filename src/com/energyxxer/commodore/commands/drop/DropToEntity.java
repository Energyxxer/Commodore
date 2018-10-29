package com.energyxxer.commodore.commands.drop;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;

public class DropToEntity implements DropDestination {
    private final Entity entity;
    private final Type slot;

    public DropToEntity(Entity entity, Type slot) {
        this.entity = entity;
        this.slot = slot;
        assertSlot(slot);
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("entity \be0 " + slot, entity);
    }
}
