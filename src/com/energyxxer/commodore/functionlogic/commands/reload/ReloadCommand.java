package com.energyxxer.commodore.functionlogic.commands.reload;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements Command {
    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "reload");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.reload");
    }
}
