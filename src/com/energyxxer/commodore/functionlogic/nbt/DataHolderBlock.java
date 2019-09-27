package com.energyxxer.commodore.functionlogic.nbt;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import org.jetbrains.annotations.NotNull;

public class DataHolderBlock implements DataHolder {
    @NotNull
    private CoordinateSet pos;

    public DataHolderBlock(@NotNull CoordinateSet pos) {
        this.pos = pos;
    }

    @NotNull
    @Override
    public String resolve() {
        return "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }

    @Override
    public void assertSingle() {
    }

    public void assertAvailable() {
    }

    @NotNull
    public CoordinateSet getPos() {
        return pos;
    }

    @Override
    public @NotNull String getTextComponentKey() {
        return "block";
    }

    @Override
    public @NotNull String getTextComponentValue() {
        return pos.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }
}
