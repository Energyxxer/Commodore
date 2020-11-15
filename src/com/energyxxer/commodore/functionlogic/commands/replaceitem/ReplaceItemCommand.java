package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.functionlogic.commands.item.ItemHolder;
import com.energyxxer.commodore.functionlogic.commands.item.ItemReplaceCommand;
import com.energyxxer.commodore.item.Item;

@Deprecated
public abstract class ReplaceItemCommand extends ItemReplaceCommand {

    public ReplaceItemCommand(ItemHolder target, Item item) {
        super(target, item);
    }

    public ReplaceItemCommand(ItemHolder target, Item item, int count) {
        super(target, item, count);
    }
}
