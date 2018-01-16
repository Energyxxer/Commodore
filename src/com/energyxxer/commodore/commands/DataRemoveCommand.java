package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.nbt.path.NBTPath;

public class DataRemoveCommand extends DataCommand {

    private NBTPath path;

    public DataRemoveCommand(Entity entity, NBTPath path) {
        super(entity);
        this.path = path;
    }

    public DataRemoveCommand(CoordinateSet pos, NBTPath path) {
        super(pos);
        this.path = path;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "data remove " + (entity != null ? "entity " + CommandUtils.getRawReference(entity, sender) : "block " + pos.toString()) + " " + path;
    }
}
