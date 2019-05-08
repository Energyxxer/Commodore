package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class DataGetCommand extends DataCommand {

    @Nullable
    private final NBTPath path;
    private final double scale;

    public DataGetCommand(@NotNull Entity entity) {
        this(entity, null);
    }

    public DataGetCommand(@NotNull Entity entity, @Nullable NBTPath path) {
        this(entity, path, 1);
    }

    public DataGetCommand(@NotNull Entity entity, @Nullable NBTPath path, double scale) {
        super(entity);
        entity.assertSingle();
        this.path = path;
        this.scale = scale;
        assertFinite(scale, "scale");
    }

    public DataGetCommand(@NotNull CoordinateSet pos) {
        this(pos, null);
    }

    public DataGetCommand(@NotNull CoordinateSet pos, @Nullable NBTPath path) {
        this(pos, path, 1);
    }

    public DataGetCommand(@NotNull CoordinateSet pos, @Nullable NBTPath path, double scale) {
        super(pos);
        this.path = path;
        this.scale = scale;
        assertFinite(scale, "scale");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        if(entity != null) return new CommandResolution(execContext, "data get entity " + entity + ((path != null) ? (" " + path + (scale != 1 ? " " + CommandUtils.numberToPlainString(scale) : "")) : ""));
        return new CommandResolution(execContext, "data get block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + ((path != null) ? (" " + path + (scale != 1 ? " " + CommandUtils.numberToPlainString(scale) : "")) : ""));
    }

}
