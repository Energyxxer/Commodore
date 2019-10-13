package com.energyxxer.commodore.functionlogic.commands.spectate;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpectateStartCommand extends SpectateCommand {
    @NotNull
    private final Entity target;
    @Nullable
    private final Entity spectator;

    public SpectateStartCommand(@NotNull Entity target) {
        this(target, null);
    }

    public SpectateStartCommand(@NotNull Entity target, @Nullable Entity spectator) {
        this.target = target;
        this.spectator = spectator;

        target.assertSingle("TARGET");
        if (spectator != null) {
            spectator.assertSingle("SPECTATOR");
            spectator.assertPlayer("SPECTATOR");
        }
    }

    @Override
    public void assertAvailable() {
        super.assertAvailable();
        target.assertAvailable();
        if (spectator != null) spectator.assertAvailable();
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spectate " + target.toString() + (spectator != null ? " " + spectator.toString() : ""));
    }
}
