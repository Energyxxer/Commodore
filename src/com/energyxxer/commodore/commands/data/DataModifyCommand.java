package com.energyxxer.commodore.commands.data;

import com.energyxxer.commodore.commands.CommandDelegateResolution;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DataModifyCommand extends DataCommand {

    public static ModifyOperation APPEND() { return new ModifyOperation("append"); }

    public static ModifyOperation INSERT(InsertOrder order, int index) { return new ModifyOperation("insert " + order.toString().toLowerCase() + " " + index); }
    public static ModifyOperation MERGE() { return new ModifyOperation("merge"); }
    public static ModifyOperation PREPEND() { return new ModifyOperation("prepend"); }
    public static ModifyOperation SET() { return new ModifyOperation("set"); }

    public enum InsertOrder {
        BEFORE, AFTER
    }

    private final NBTPath targetPath;
    private final ModifyOperation operation;
    private final ModifySource source;

    public DataModifyCommand(Entity entity, NBTPath targetPath, ModifyOperation operation, ModifySource source) {
        super(entity);
        this.targetPath = targetPath;
        this.operation = operation;
        this.source = source;
    }

    public DataModifyCommand(CoordinateSet pos, NBTPath targetPath, ModifyOperation operation, ModifySource source) {
        super(pos);
        this.targetPath = targetPath;
        this.operation = operation;
        this.source = source;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();

        StringBuilder sb = new StringBuilder("data modify ");

        if(entity != null) {
            sb.append("entity \be0 ");
            embeddables.add(entity);
        } else {
            sb.append("block ");
            sb.append(pos.getAs(Coordinate.DisplayMode.BLOCK_POS));
            sb.append(' ');
        }

        sb.append(targetPath);
        sb.append(' ');

        sb.append(operation.operation);
        sb.append(' ');

        CommandDelegateResolution sourceResolution = source.resolve();

        sb.append(sourceResolution.attachment.replace("\be#", "\be" + embeddables.size()));
        embeddables.addAll(sourceResolution.embeddables);

        return new CommandResolution(execContext, sb.toString(), embeddables.toArray(new CommandEmbeddable[0]));
    }
}

class ModifyOperation {
    final String operation;

    public ModifyOperation(String operation) {
        this.operation = operation;
    }
}

interface ModifySource {
    CommandDelegateResolution resolve();
}