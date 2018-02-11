package com.energyxxer.commodore.commands.worldborder;

import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDamageBuffer extends WorldBorderCommand {
    private final int distance;

    public WorldBorderSetDamageBuffer(int distance) {
        this.distance = distance;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder damage buffer " + distance);
    }
}
