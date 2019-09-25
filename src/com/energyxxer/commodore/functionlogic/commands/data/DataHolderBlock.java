package com.energyxxer.commodore.functionlogic.commands.data;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;

public class DataHolderBlock implements DataHolder {
    private CoordinateSet pos;

    public DataHolderBlock(CoordinateSet pos) {
        this.pos = pos;
    }

    @Override
    public String resolve() {
        return "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS);
    }

    @Override
    public void assertSingle() {
    }

    public void assertAvailable() {
    }

    public CoordinateSet getPos() {
        return pos;
    }
}
