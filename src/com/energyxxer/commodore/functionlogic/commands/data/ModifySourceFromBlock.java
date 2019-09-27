package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.nbt.DataHolderBlock;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public class ModifySourceFromBlock extends ModifySourceFromHolder {

    public ModifySourceFromBlock(@NotNull CoordinateSet pos) {
        this(pos, null);
    }

    public ModifySourceFromBlock(@NotNull CoordinateSet pos, @Nullable NBTPath sourcePath) {
        super(new DataHolderBlock(pos), sourcePath);
    }
}
