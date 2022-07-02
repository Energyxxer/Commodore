package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.nbt.TagCompound;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NBTArgument implements SelectorArgument {
    @NotNull
    public final TagCompound nbt;
    public boolean negated = false;

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

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBTArgument that = (NBTArgument) o;
        return negated == that.negated &&
                nbt.equals(that.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nbt, negated);
    }
}
