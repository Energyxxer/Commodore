package com.energyxxer.commodore.functionlogic.commands.loot;

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

    /**
     * Creates a /drop block destination with a "insert" mode.
     * As such, it will place the drop source in the specified slot, or logically succeeding slots if multiple items
     * are dropped.
     *
     * @param pos The location of the container block to drop to
     * @param slot The from which items will be inserted
     * */
    public LootReplaceBlock(@NotNull CoordinateSet pos, @NotNull Type slot) {
        assertSlot(slot);
        this.slot = slot;
        this.pos = pos;
    }

    @NotNull
    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("replace block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + slot);
    }
}
