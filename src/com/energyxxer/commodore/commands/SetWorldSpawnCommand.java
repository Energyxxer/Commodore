package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class SetWorldSpawnCommand implements Command {

    private CoordinateSet pos;

    public SetWorldSpawnCommand(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "setworldspawn " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }
}
