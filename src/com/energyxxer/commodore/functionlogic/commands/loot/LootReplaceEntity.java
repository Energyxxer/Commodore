package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;

public class LootReplaceEntity implements LootCommand.LootDestination {
    @NotNull
    private final Entity entity;
    @NotNull
    private final Type slot;

    public LootReplaceEntity(@NotNull Entity entity, @NotNull Type slot) {
        this.entity = entity;
        this.slot = slot;
        assertSlot(slot);
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("replace entity " + entity + " " + slot);
    }
}
