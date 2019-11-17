package com.energyxxer.commodore.functionlogic.commands.spreadplayers;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class SpreadPlayersCommand implements Command {
    @NotNull
    private final CoordinateSet center;
    private final double spreadDistance;
    private final double maxRange;
    private final boolean respectTeams;
    @NotNull
    private final Entity targets;

    public SpreadPlayersCommand(@NotNull Entity targets, @NotNull CoordinateSet center, double spreadDistance, double maxRange, boolean respectTeams) {
        this.center = center;
        this.spreadDistance = spreadDistance;
        this.maxRange = maxRange;
        this.respectTeams = respectTeams;
        this.targets = targets;
        targets.assertEntityFriendly();

        assertFinite(spreadDistance, "spreadDistance");
        assertFinite(maxRange, "maxRange");

        if(spreadDistance < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Spread distance must not be less than 0.0, found " + spreadDistance, spreadDistance, "SPREAD_DISTANCE");
        if(maxRange < spreadDistance+1) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Max range must not be less than " + (spreadDistance + 1) + " (spread distance + 1), found " + maxRange, maxRange, "MAX_RANGE");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spreadplayers " + center.getXZAs(Coordinate.DisplayMode.ENTITY_POS) + " " + CommandUtils.numberToPlainString(spreadDistance) + " " + CommandUtils.numberToPlainString(maxRange) + " " + respectTeams + " " + targets);
    }

    @Override
    public void assertAvailable() {
        targets.assertAvailable();
    }
}
