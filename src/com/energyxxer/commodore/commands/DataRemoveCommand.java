package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class DataRemoveCommand extends DataCommand {

    private String path;

    public DataRemoveCommand(Entity entity, String path) {
        super(entity);
        this.path = path;
    }

    public DataRemoveCommand(CoordinateSet pos, String path) {
        super(pos);
        this.path = path;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data remove " + (entity != null ? "entity " + CommandUtils.getRawReference(entity, sender) : "block " + pos.toString()) + " " + path;
    }
}
