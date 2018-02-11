package com.energyxxer.commodore.commands.replaceitem;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import org.jetbrains.annotations.NotNull;

public class ReplaceItemBlockCommand extends ReplaceItemCommand {

    private final CoordinateSet pos;
    private final String slot;
    private final Item item;
    private final int count;

    public ReplaceItemBlockCommand(CoordinateSet pos, String slot, Item item) {
        this(pos, slot, item, 1);
    }

    public ReplaceItemBlockCommand(CoordinateSet pos, String slot, Item item, int count) {
        this.pos = pos;
        this.slot = slot;
        this.item = item;
        this.count = count;

        if(!item.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual items");
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "replaceitem block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + slot + " " + item + (count != 1 ? " " + count : ""));
    }
}
