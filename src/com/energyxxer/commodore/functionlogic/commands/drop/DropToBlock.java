package com.energyxxer.commodore.functionlogic.commands.drop;

import com.energyxxer.commodore.functionlogic.commands.CommandDelegateResolution;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;

public class DropToBlock implements DropDestination {
    private final CoordinateSet pos;
    private final String arguments;

    /**
     * Creates a /drop block destination with a "distribute" mode.
     * As such, it will place the drop source in whatever slots are available.
     *
     * @param pos The location of the container block to drop to
     * */
    public DropToBlock(CoordinateSet pos) {
        this.pos = pos;
        this.arguments = "distribute";
    }

    /**
     * Creates a /drop block destination with a "insert" mode.
     * As such, it will place the drop source in the specified slot, or logically succeeding slots if multiple items
     * are dropped.
     *
     * @param pos The location of the container block to drop to
     * @param slot The from which items will be inserted
     * */
    public DropToBlock(CoordinateSet pos, Type slot) {
        this(pos, slot, false);
    }

    /**
     * Creates a /drop block destination with a "insert" mode.
     * As such, it will place the drop source in the specified slot, or logically succeeding slots if multiple items
     * are dropped.
     *
     * @param pos The location of the container block to drop to
     * @param slot The from which items will be inserted
     * @param replace Whether items in conflicting slots of the container should be replaced or kept
     * */
    public DropToBlock(CoordinateSet pos, Type slot, boolean replace) {
        assertSlot(slot);
        this.pos = pos;
        this.arguments = "insert " + slot + (replace ? " replace" : "");
    }

    @Override
    public CommandDelegateResolution resolve() {
        return new CommandDelegateResolution("block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + arguments);
    }
}
