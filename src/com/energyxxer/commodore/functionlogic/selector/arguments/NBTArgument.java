package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;

public class NBTArgument implements SelectorArgument {
    private final TagCompound nbt;
    private boolean negated = false;

    public NBTArgument(TagCompound nbt) {
        this(nbt, false);
    }

    public NBTArgument(TagCompound nbt, boolean negated) {
        this.nbt = nbt;
        this.negated = negated;
    }

    @Override
    public String getArgumentString() {
        return "nbt=" + (negated ? "!" : "") + nbt.toHeadlessString();
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public NBTArgument clone() {
        return new NBTArgument(nbt.clone(), negated);
    }

    @Override
    public String getKey() {
        return "nbt";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
