package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.DataHolder;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderEntity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DataModifyCommand extends DataCommand {

    @NotNull
    @Contract(" -> new")
    public static ModifyOperation APPEND() { return new ModifyOperation("append"); }

    @NotNull
    @Contract("_ -> new")
    public static ModifyOperation INSERT(int index) { return new ModifyOperation("insert " + index); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation MERGE() { return new ModifyOperation("merge"); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation PREPEND() { return new ModifyOperation("prepend"); }
    @NotNull
    @Contract(" -> new")
    public static ModifyOperation SET() { return new ModifyOperation("set"); }

    @NotNull
    private final NBTPath targetPath;
    @NotNull
    private final ModifyOperation operation;
    @NotNull
    private final ModifySource source;

    public DataModifyCommand(@NotNull Entity entity, @NotNull NBTPath targetPath, @NotNull ModifyOperation operation, @NotNull ModifySource source) {
        this(new DataHolderEntity(entity), targetPath, operation, source);
    }

    public DataModifyCommand(@NotNull CoordinateSet pos, @NotNull NBTPath targetPath, @NotNull ModifyOperation operation, @NotNull ModifySource source) {
        this(new DataHolderBlock(pos), targetPath, operation, source);
    }

    public DataModifyCommand(@NotNull DataHolder holder, @NotNull NBTPath targetPath, @NotNull ModifyOperation operation, @NotNull ModifySource source) {
        super(holder);
        this.targetPath = targetPath;
        this.operation = operation;
        this.source = source;

        holder.assertSingle();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {

        String sb = "data modify " + holder.resolve() +
                ' ' +
                targetPath +
                ' ' +
                operation.operation +
                ' ' +
                source.resolve();
        return new CommandResolution(execContext, sb);
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.data_modify");
        super.assertAvailable();
        source.assertAvailable();
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

        void assertAvailable();
    }
}