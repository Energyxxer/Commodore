package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

public class NBTArgument implements SelectorArgument {
    @NotNull
    private final TagCompound nbt;
    private boolean negated = false;

    public NBTArgument(@NotNull TagCompound nbt) {
        this(nbt, false);
    }

    public NBTArgument(@NotNull TagCompound nbt, boolean negated) {
        this.nbt = nbt;
        this.negated = negated;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "nbt=" + (negated ? "!" : "") + nbt.toHeadlessString();
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @NotNull
    @Override
    public NBTArgument clone() {
        return new NBTArgument(nbt.clone(), negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "nbt";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
