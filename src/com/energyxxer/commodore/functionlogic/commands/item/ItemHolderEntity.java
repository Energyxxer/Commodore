package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class ItemHolderEntity extends ItemHolder {
    @NotNull
    private Entity entity;

    public ItemHolderEntity(@NotNull Entity entity, @NotNull Type slot) {
        super(slot);
        this.entity = entity;
        entity.assertEntityFriendly();
    }

    @Override
    public @NotNull String resolve() {
        return "entity " + entity.toString() + " " + this.slot;
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }

    @Override
    public void assertSingle() {
        entity.assertSingle();
    }
}
