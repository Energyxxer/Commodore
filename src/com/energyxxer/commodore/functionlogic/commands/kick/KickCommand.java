package com.energyxxer.commodore.functionlogic.commands.kick;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KickCommand implements Command {

    @NotNull
    private final Entity player;
    @Nullable
    private final String reason;

    public KickCommand(@NotNull Entity player) {
        this(player, null);
    }

    public KickCommand(@NotNull Entity player, @Nullable String reason) {
        this.player = player;
        this.reason = reason;
        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "kick " + player + (reason != null ? " " + reason : ""));
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.kick");
    }
}
