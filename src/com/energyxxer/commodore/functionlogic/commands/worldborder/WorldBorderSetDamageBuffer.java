package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

public class WorldBorderSetDamageBuffer extends WorldBorderCommand {
    private final double distance;

    public WorldBorderSetDamageBuffer(double distance) {
        this.distance = distance;
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder damage buffer " + CommandUtils.numberToPlainString(distance));
    }
}
