package com.energyxxer.commodore.functionlogic.commands.save;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class SetAutosaveCommand implements Command {

    private final boolean enable;

    public SetAutosaveCommand(boolean enable) {
        this.enable = enable;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "save-" + (enable ? "on" : "off"));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
    }
}
