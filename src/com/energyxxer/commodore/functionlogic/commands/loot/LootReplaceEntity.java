package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.CommodoreException;
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
    private int count;

    public LootReplaceEntity(@NotNull Entity entity, @NotNull Type slot) {
        this(entity, slot, -1);
    }

    public LootReplaceEntity(@NotNull Entity entity, @NotNull Type slot, int count) {
        this.entity = entity;
        entity.assertEntityFriendly();
        this.slot = slot;
        assertSlot(slot);

        if(count < -1) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than -1, found " + count, count);
        }
        this.count = count;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("replace entity " + entity + " " + slot + (count != -1 ? " " + count : ""));
    }

    public void assertAvailable() {
        entity.assertAvailable();
    }
}
