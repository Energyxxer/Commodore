package com.energyxxer.commodore.functionlogic.commands.spreadplayers;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;

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
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spreadplayers " + center.getXZAs(Coordinate.DisplayMode.ENTITY_POS) + " " + CommandUtils.numberToPlainString(spreadDistance) + " " + CommandUtils.numberToPlainString(maxRange) + " " + respectTeams + " " + targets);
    }

}
