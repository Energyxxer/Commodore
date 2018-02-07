package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.nbt.NBTPath;

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
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data remove entity \be0 " + path, entity);
        return new CommandResolution(execContext, "data remove block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path);
    }
}
