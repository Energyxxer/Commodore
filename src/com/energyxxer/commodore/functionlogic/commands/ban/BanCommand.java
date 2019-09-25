package com.energyxxer.commodore.functionlogic.commands.ban;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BanCommand implements Command {

    @NotNull
    private final Entity profile;
    @Nullable
    private final String reason;

    public BanCommand(@NotNull Entity profile) {
        this(profile, null);
    }

    public BanCommand(@NotNull Entity profile, @Nullable String reason) {
        this.profile = profile;
        this.reason = reason;
        profile.assertGameProfile();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "ban " + profile.gameProfileToString() + (reason != null ? " " + reason : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("server_commands");
        profile.assertAvailable();
    }
}
