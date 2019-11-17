package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;
import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class ReplaceItemEntityCommand extends ReplaceItemCommand {
    @NotNull
    private final Entity entity;
    @NotNull
    private final Type slot;
    @NotNull
    private final Item item;
    private final int count;

    public ReplaceItemEntityCommand(@NotNull Entity entity, @NotNull Type slot, @NotNull Item item) {
        this(entity, slot, item, 1);
    }

    public ReplaceItemEntityCommand(@NotNull Entity entity, @NotNull Type slot, @NotNull Item item, int count) {
        this.entity = entity;
        this.slot = slot;
        this.item = item;
        this.count = count;

        entity.assertEntityFriendly();
        assertSlot(slot);
        assertStandalone(item.getItemType());
        if(count < 1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than 1, found " + count, count, "COUNT");
        if(count > 64) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be more than 64, found " + count, count, "COUNT");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "replaceitem entity " + entity + " " + slot + " " + item + (count != 1 ? " " + count : ""));
    }

    @Override
    public void assertAvailable() {
        entity.assertAvailable();
    }
}
