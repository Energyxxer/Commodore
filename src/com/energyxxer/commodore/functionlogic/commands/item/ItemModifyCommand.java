package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.defaults.ItemModifier;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class ItemModifyCommand implements Command {
    @NotNull
    private ItemHolder target;
    @NotNull
    private Type modifier;

    public ItemModifyCommand(@NotNull ItemHolder target, @NotNull Type modifier) {
        this.target = target;
        this.modifier = modifier;
        assertType(modifier, ItemModifier.CATEGORY);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.item");
        target.assertAvailable();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "item " + target.resolve() + " modify " + modifier.toSafeString());
    }
}
