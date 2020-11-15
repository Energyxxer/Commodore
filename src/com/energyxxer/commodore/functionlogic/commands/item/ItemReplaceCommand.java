package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class ItemReplaceCommand implements Command {
    private ItemHolder target;
    private Item item;
    private int count;

    public ItemReplaceCommand(ItemHolder target, Item item) {
        this(target, item, 1);
    }

    public ItemReplaceCommand(ItemHolder target, Item item, int count) {
        this.target = target;
        this.item = item;
        this.count = count;

        assertStandalone(item.getItemType());
        if(count < 1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than 1, found " + count, count, "COUNT");
        if(count > 64) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be more than 64, found " + count, count, "COUNT");
    }

    @Override
    public void assertAvailable() {
        target.assertAvailable();
        item.assertAvailable();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        if(VersionFeatureManager.getBoolean("command.item", false)) {
            return new CommandResolution(execContext, "item " + target.resolve() + " replace " + item + (count != 1 ? " " + count : ""));
        } else {
            return new CommandResolution(execContext, "replaceitem " + target.resolve() + " " + item + (count != 1 ? " " + count : ""));
        }
    }
}
