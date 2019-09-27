package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.DataHolder;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class DataRemoveCommand extends DataCommand {

    @NotNull
    private final NBTPath path;

    public DataRemoveCommand(@NotNull Entity entity, @NotNull NBTPath path) {
        this(new DataHolderEntity(entity), path);
    }

    public DataRemoveCommand(@NotNull CoordinateSet pos, @NotNull NBTPath path) {
        this(new DataHolderBlock(pos), path);
    }

    public DataRemoveCommand(@NotNull DataHolder holder, @NotNull NBTPath path) {
        super(holder);
        this.path = path;

        holder.assertSingle();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "data remove " + holder.resolve() + " " + path);
    }
}
