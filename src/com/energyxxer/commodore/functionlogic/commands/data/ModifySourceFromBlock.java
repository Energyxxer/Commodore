package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModifySourceFromBlock implements DataModifyCommand.ModifySource {
    @NotNull
    private final CoordinateSet pos;
    @Nullable
    private final NBTPath sourcePath;

    public ModifySourceFromBlock(@NotNull CoordinateSet pos) {
        this(pos, null);
    }

    public ModifySourceFromBlock(@NotNull CoordinateSet pos, @Nullable NBTPath sourcePath) {
        this.pos = pos;
        this.sourcePath = sourcePath;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("from block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + ((sourcePath != null) ? " " + sourcePath : ""));
    }
}
