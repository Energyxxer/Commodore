package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;

public abstract class DataCommand implements Command {
    /*
    * data get entity <selector> <path> <scale>
    * data remove entity <selector> <path>
    * data merge entity <selector> <nbt>
    * data get block <x y z> <path> <scale>
    * data remove block <x y z> <path>
    * data merge block <x y z> <nbt>
    *
    * DataCommand(Entity entity, String path, float scale) //data get entity <selector> <path> <scale>
    * DataCommand(Entity entity, String path) //data remove entity <selector> <path>
    * DataCommand(Entity entity, TagCompound nbt) //data merge entity <selector> <nbt>
    *
    * DataCommand(CoordinateSet pos, String path, float scale) //data get block <x y z> <path> <scale>
    * DataCommand(CoordinateSet pos, String path) //data remove block <x y z> <path>
    * DataCommand(CoordinateSet pos, TagCompound nbt) //data merge block <x y z> <nbt>
    * */

    protected Entity entity;
    protected CoordinateSet pos;

    public DataCommand(Entity entity) {
        this.entity = entity;
    }

    public DataCommand(CoordinateSet pos) {
        this.pos = pos;
    }
}
