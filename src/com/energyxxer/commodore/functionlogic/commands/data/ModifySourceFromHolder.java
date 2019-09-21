package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModifySourceFromHolder implements DataModifyCommand.ModifySource {
    @NotNull private DataHolder holder;
    @Nullable private NBTPath sourcePath;

    public ModifySourceFromHolder(@NotNull DataHolder holder, @Nullable NBTPath sourcePath) {
        this.holder = holder;
        this.sourcePath = sourcePath;

        holder.assertSingle();
    }

    @Override
    public @NotNull String resolve() {
        return "from " + holder.resolve() + (sourcePath != null ? " " + sourcePath : "");
    }

    @Override
    public void assertAvailable() {
        holder.assertAvailable();
    }
}
