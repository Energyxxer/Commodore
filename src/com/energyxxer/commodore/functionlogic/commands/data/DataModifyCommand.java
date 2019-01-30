package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DataModifyCommand extends DataCommand {

    @NotNull
    @Contract(" -> new")
    public static ModifyOperation APPEND() { return new ModifyOperation("append"); }

    @NotNull
    @Contract("_, _ -> new")
    public static ModifyOperation INSERT(@NotNull InsertOrder order, int index) { return new ModifyOperation("insert " + order.toString().toLowerCase() + " " + index); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation MERGE() { return new ModifyOperation("merge"); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation PREPEND() { return new ModifyOperation("prepend"); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation SET() { return new ModifyOperation("set"); }

    public enum InsertOrder {
        BEFORE, AFTER
    }

    @NotNull
    private final NBTPath targetPath;
    @NotNull
    private final ModifyOperation operation;
    @NotNull
    private final ModifySource source;

    public DataModifyCommand(@NotNull Entity entity, @NotNull NBTPath targetPath, @NotNull ModifyOperation operation, @NotNull ModifySource source) {
        super(entity);
        this.targetPath = targetPath;
        this.operation = operation;
        this.source = source;

        entity.assertSingle();
    }

    public DataModifyCommand(@NotNull CoordinateSet pos, @NotNull NBTPath targetPath, @NotNull ModifyOperation operation, @NotNull ModifySource source) {
        super(pos);
        this.targetPath = targetPath;
        this.operation = operation;
        this.source = source;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {

        StringBuilder sb = new StringBuilder("data modify ");

        if(entity != null) {
            sb.append("entity ").append(entity).append(" ");
        } else {
            sb.append("block ");
            sb.append(pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
            sb.append(' ');
        }

        sb.append(targetPath);
        sb.append(' ');

        sb.append(operation.operation);
        sb.append(' ');

        sb.append(source.resolve());

        return new CommandResolution(execContext, sb.toString());
    }

    public static class ModifyOperation {
        @NotNull
        final String operation;

        public ModifyOperation(@NotNull String operation) {
            this.operation = operation;
        }
    }

    public interface ModifySource {
        @NotNull
        String resolve();
    }
}