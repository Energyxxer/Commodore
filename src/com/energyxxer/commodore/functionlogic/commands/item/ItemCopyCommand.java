package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemModifier;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class ItemCopyCommand implements Command {
    private ItemHolder to;
    private ItemHolder from;
    @Nullable
    private Type modifier;

    public ItemCopyCommand(ItemHolder to, ItemHolder from) {
        this(to, from, null);
    }

    public ItemCopyCommand(ItemHolder to, ItemHolder from, @Nullable Type modifier) {
        this.to = to;
        this.from = from;
        from.assertSingle();
        this.modifier = modifier;

        if(modifier != null) assertType(modifier, ItemModifier.CATEGORY);
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "item replace " + to.resolve() + " from " + from.resolve() + (modifier != null ? " " + modifier.toSafeString() : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.item");
        from.assertAvailable();
        to.assertAvailable();
    }
}
