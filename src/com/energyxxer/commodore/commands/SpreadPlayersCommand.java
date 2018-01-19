package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class SpreadPlayersCommand implements Command {
    private CoordinateSet center;
    private double spreadDistance;
    private double maxRange;
    private boolean respectTeams;
    private Entity targets;

    public SpreadPlayersCommand(Entity targets, CoordinateSet center, double spreadDistance, double maxRange, boolean respectTeams) {
        this.center = center;
        this.spreadDistance = spreadDistance;
        this.maxRange = maxRange;
        this.respectTeams = respectTeams;
        this.targets = targets;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "spreadplayers " + center.getX().getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + center.getZ().getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + CommandUtils.toString(spreadDistance) + " " + CommandUtils.toString(maxRange) + " " + respectTeams + " " + targets.getSelectorAs(sender);
    }
}
