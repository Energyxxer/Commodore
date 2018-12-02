package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;

public class ModifySourceFromBlock implements DataModifyCommand.ModifySource {
    private final CoordinateSet pos;
    private final NBTPath sourcePath;

    public ModifySourceFromBlock(CoordinateSet pos, NBTPath sourcePath) {
        this.pos = pos;
        this.sourcePath = sourcePath;
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("from block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + sourcePath);
    }
}
