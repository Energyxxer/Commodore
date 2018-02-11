package com.energyxxer.commodore.commands.spreadplayers;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class SpreadPlayersCommand implements Command {
    private final CoordinateSet center;
    private final double spreadDistance;
    private final double maxRange;
    private final boolean respectTeams;
    private final Entity targets;

    public SpreadPlayersCommand(Entity targets, CoordinateSet center, double spreadDistance, double maxRange, boolean respectTeams) {
        this.center = center;
        this.spreadDistance = spreadDistance;
        this.maxRange = maxRange;
        this.respectTeams = respectTeams;
        this.targets = targets;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spreadplayers " + center.getX().getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + center.getZ().getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + CommandUtils.toString(spreadDistance) + " " + CommandUtils.toString(maxRange) + " " + respectTeams + " \be0", targets);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(targets.getScoreboardAccesses());
    }
}
