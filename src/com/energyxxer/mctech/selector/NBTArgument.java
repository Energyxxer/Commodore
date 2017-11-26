package com.energyxxer.mctech.selector;

import com.energyxxer.mctech.nbt.TagCompound;

public class NBTArgument implements SelectorArgument {
    private TagCompound nbt;
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
}
