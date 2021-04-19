package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;

public class LootReplaceBlock implements LootCommand.LootDestination {
    @NotNull
    private final CoordinateSet pos;
    @NotNull
    private final Type slot;
    private int count;

    /**
     * Creates a /drop block destination with a "insert" mode.
     * As such, it will place the drop source in the specified slot, or logically succeeding slots if multiple items
     * are dropped.
     *  @param pos The location of the container block to drop to
     * @param slot The slot in which items will be inserted */
    public LootReplaceBlock(@NotNull CoordinateSet pos, @NotNull Type slot) {
        this(pos, slot, -1);
    }

    /**
     * Creates a /drop block destination with a "insert" mode.
     * As such, it will place the drop source in the specified slot, or logically succeeding slots if multiple items
     * are dropped.
     *
     * @param pos The location of the container block to drop to
     * @param slot The slot in which items will be inserted
     * @param count The max number of slots to replace
     * */
    public LootReplaceBlock(@NotNull CoordinateSet pos, @NotNull Type slot, int count) {
        assertSlot(slot);
        this.slot = slot;
        this.pos = pos;

        if(count < -1) {
            throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than -1, found " + count, count);
        }
        this.count = count;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("replace block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + slot.toSafeString() + (count != -1 ? " " + count : ""));
    }
}
