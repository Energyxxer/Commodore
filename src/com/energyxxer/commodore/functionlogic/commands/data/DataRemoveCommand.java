package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class DataRemoveCommand extends DataCommand {

    @NotNull
    private final NBTPath path;

    public DataRemoveCommand(@NotNull Entity entity, @NotNull NBTPath path) {
        super(entity);
        this.path = path;
    }

    public DataRemoveCommand(@NotNull CoordinateSet pos, @NotNull NBTPath path) {
        super(pos);
        this.path = path;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data remove entity " + entity + " " + path);
        return new CommandResolution(execContext, "data remove block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + path);
    }
}
