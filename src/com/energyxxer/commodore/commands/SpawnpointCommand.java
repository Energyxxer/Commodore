package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class SpawnpointCommand implements Command {

    private Entity player;
    private CoordinateSet pos;

    public SpawnpointCommand(Entity player, CoordinateSet pos) {
        this.player = player;
        this.pos = pos;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "spawnpoint " + player.getSelectorAs(sender) + " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }
}
