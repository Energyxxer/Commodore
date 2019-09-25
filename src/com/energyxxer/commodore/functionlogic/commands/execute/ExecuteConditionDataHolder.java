package com.energyxxer.commodore.functionlogic.commands.execute;

import com.energyxxer.commodore.functionlogic.commands.data.DataHolder;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;

public class ExecuteConditionDataHolder extends ExecuteCondition {

    @NotNull
    private final DataHolder holder;
    @NotNull
    private final NBTPath path;

    public ExecuteConditionDataHolder(@NotNull ConditionType flowController, @NotNull DataHolder holder, @NotNull NBTPath path) {
        super(flowController);
        this.holder = holder;
        this.path = path;

        holder.assertSingle();
    }

    @Override
    public @NotNull SubCommandResult getSubCommand(ExecutionContext execContext) {
        return new SubCommandResult(execContext, this.getStarter() + "data " + holder + " " + path);
    }

    @Override
    public boolean isIdempotent() {
        return true;
    }

    @Override
    public boolean isSignificant() {
        return true;
    }

    @Override
    public boolean isAbsolute() {
        return false;
    }

    @NotNull
    public DataHolder getHolder() {
        return holder;
    }

    @NotNull
    public NBTPath getPath() {
        return path;
    }

    @Override
    public void assertAvailable() {
        holder.assertAvailable();
    }
}
