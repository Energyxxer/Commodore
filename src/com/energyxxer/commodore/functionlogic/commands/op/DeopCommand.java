package com.energyxxer.commodore.functionlogic.commands.op;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class DeopCommand implements Command {

    @NotNull
    private final Entity profile;

    public DeopCommand(@NotNull Entity profile) {
        this.profile = profile;
        profile.assertGameProfile();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "deop " + profile.gameProfileToString());
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
        profile.assertAvailable();
    }
}
