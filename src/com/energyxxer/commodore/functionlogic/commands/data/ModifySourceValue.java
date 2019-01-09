package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.nbt.NBTTag;
import org.jetbrains.annotations.NotNull;

public class ModifySourceValue implements DataModifyCommand.ModifySource {
    @NotNull
    private final NBTTag value;

    public ModifySourceValue(@NotNull NBTTag value) {
        this.value = value;
    }

    @NotNull
    @Override
    public String resolve() {
        return "value " + value.toHeadlessString();
    }
}
