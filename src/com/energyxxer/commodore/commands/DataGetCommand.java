package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.NBTPath;

public class DataGetCommand extends DataCommand {

    private NBTPath path;
    private double scale = 1;

    public DataGetCommand(Entity entity, NBTPath path) {
        this(entity, path, 1);
    }

    public DataGetCommand(Entity entity, NBTPath path, double scale) {
        super(entity);
        if(entity.getLimit() < 0 || entity.getLimit() > 1) throw new IllegalArgumentException("Only one entity is allowed, but passed entity (" + entity + ") allows for more than one.");
        this.path = path;
        this.scale = scale;
    }

    public DataGetCommand(CoordinateSet pos, NBTPath path) {
        this(pos, path, 1);
    }

    public DataGetCommand(CoordinateSet pos, NBTPath path, double scale) {
        super(pos);
        this.path = path;
        this.scale = scale;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data get " + (entity != null ? "entity " + CommandUtils.getRawReference(entity, sender) : "block " + pos.toString()) + " " + path + (scale != 1 ? String.valueOf(scale) : "");
    }
}
