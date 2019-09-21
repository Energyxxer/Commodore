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
        this(new DataHolderEntity(entity), path, scale);
    }

    public DataGetCommand(@NotNull CoordinateSet pos) {
        this(pos, null);
    }

    public DataGetCommand(@NotNull CoordinateSet pos, @Nullable NBTPath path) {
        this(pos, path, 1);
    }

    public DataGetCommand(@NotNull CoordinateSet pos, @Nullable NBTPath path, double scale) {
        this(new DataHolderBlock(pos), path, scale);
    }

    public DataGetCommand(@NotNull DataHolder holder) {
        this(holder, null);
    }

    public DataGetCommand(@NotNull DataHolder holder, @Nullable NBTPath path) {
        this(holder, path, 1);
    }

    public DataGetCommand(@NotNull DataHolder holder, @Nullable NBTPath path, double scale) {
        super(holder);
        this.path = path;
        this.scale = scale;
        holder.assertSingle();
        assertFinite(scale, "scale");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "data get " + holder.resolve() + ((path != null) ? (" " + path + (scale != 1 ? " " + CommandUtils.numberToPlainString(scale) : "")) : ""));
    }

}
