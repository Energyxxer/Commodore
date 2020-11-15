package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemSlot;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public abstract class ItemHolder {
    @NotNull
    protected Type slot;

    public ItemHolder(@NotNull Type slot) {
        assertType(slot, ItemSlot.CATEGORY);
        this.slot = slot;
    }

    @NotNull
    public abstract String resolve();

    public abstract void assertAvailable();
    public abstract void assertSingle();
}
