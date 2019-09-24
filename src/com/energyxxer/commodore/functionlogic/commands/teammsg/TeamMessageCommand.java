package com.energyxxer.commodore.functionlogic.commands.teammsg;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class TeamMessageCommand implements Command {
    @NotNull
    private final String message;

    public TeamMessageCommand(@NotNull String message) {
        this.message = message;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "teammsg " + message);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.teammsg");
    }
}
