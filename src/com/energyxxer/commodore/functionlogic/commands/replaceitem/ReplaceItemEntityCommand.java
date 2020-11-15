package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.functionlogic.commands.item.ItemHolderEntity;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class ReplaceItemEntityCommand extends ReplaceItemCommand {
    public ReplaceItemEntityCommand(@NotNull Entity entity, @NotNull Type slot, @NotNull Item item) {
        super(new ItemHolderEntity(entity, slot), item, 1);
    }

    public ReplaceItemEntityCommand(@NotNull Entity entity, @NotNull Type slot, @NotNull Item item, int count) {
        super(new ItemHolderEntity(entity, slot), item, count);
    }
}
