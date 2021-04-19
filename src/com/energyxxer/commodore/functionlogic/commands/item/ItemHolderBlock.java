package com.energyxxer.commodore.functionlogic.commands.item;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

public class ItemHolderBlock extends ItemHolder {
    private CoordinateSet pos;

    public ItemHolderBlock(@NotNull CoordinateSet pos, @NotNull Type slot) {
        super(slot);
        this.pos = pos;
    }

    @Override
    public @NotNull String resolve() {
        return "block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + this.slot.toSafeString();
    }

    @Override
    public void assertAvailable() {
    }

    @Override
    public void assertSingle() {
    }
}
