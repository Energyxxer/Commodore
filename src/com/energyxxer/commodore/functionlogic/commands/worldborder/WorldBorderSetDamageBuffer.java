package com.energyxxer.commodore.functionlogic.commands.worldborder;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class WorldBorderSetDamageBuffer extends WorldBorderCommand {
    private final double distance;

    public WorldBorderSetDamageBuffer(double distance) {
        this.distance = distance;

        assertFinite(distance, "distance");

        if(distance < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Distance must not be less than 0, found " + distance, distance, "DISTANCE");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "worldborder damage buffer " + CommandUtils.numberToPlainString(distance));
    }
}
