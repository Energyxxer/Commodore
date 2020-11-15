package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class ItemCopyCommand implements Command {
    private ItemHolder to;
    private ItemHolder from;

    public ItemCopyCommand(ItemHolder to, ItemHolder from) {
        this.to = to;
        this.from = from;
        from.assertSingle();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "item " + to.resolve() + " copy " + from.resolve());
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.item");
        from.assertAvailable();
        to.assertAvailable();
    }
}
