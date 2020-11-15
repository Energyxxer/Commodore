package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.functionlogic.commands.item.ItemHolderBlock;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class ReplaceItemBlockCommand extends ReplaceItemCommand {

    public ReplaceItemBlockCommand(@NotNull CoordinateSet pos, @NotNull Type slot, @NotNull Item item) {
        super(new ItemHolderBlock(pos, slot), item, 1);
    }

    public ReplaceItemBlockCommand(@NotNull CoordinateSet pos, @NotNull Type slot, @NotNull Item item, int count) {
        super(new ItemHolderBlock(pos, slot), item, count);
    }
}
