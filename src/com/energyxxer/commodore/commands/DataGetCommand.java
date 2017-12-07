package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public class DataGetCommand extends DataCommand {

    private String path;
    private double scale = 1;

    public DataGetCommand(Entity entity, String path) {
        this(entity, path, 1);
    }

    public DataGetCommand(Entity entity, String path, double scale) {
        super(entity);
        this.path = path;
        this.scale = scale;
    }

    public DataGetCommand(CoordinateSet pos, String path) {
        this(pos, path, 1);
    }

    public DataGetCommand(CoordinateSet pos, String path, double scale) {
        super(pos);
        this.path = path;
        this.scale = scale;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data get " + (entity != null ? "entity " + CommandUtils.getRawReference(entity, sender) : "block " + pos.toString()) + " " + path + (scale != 1 ? String.valueOf(scale) : "");
    }
}
