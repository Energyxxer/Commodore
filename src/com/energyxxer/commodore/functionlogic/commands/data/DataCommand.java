package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;

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

    protected final Entity entity;
    protected final CoordinateSet pos;

    public DataCommand(@NotNull Entity entity) {
        this.pos = null;
        this.entity = entity;
    }

    public DataCommand(@NotNull CoordinateSet pos) {
        this.entity = null;
        this.pos = pos;
    }

}
