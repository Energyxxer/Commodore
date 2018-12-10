package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertSlot;
import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

public class ReplaceItemBlockCommand extends ReplaceItemCommand {
    @NotNull
    private final CoordinateSet pos;
    @NotNull
    private final Type slot;
    @NotNull
    private final Item item;
    private final int count;

    public ReplaceItemBlockCommand(@NotNull CoordinateSet pos, @NotNull Type slot, @NotNull Item item) {
        this(pos, slot, item, 1);
    }

    public ReplaceItemBlockCommand(@NotNull CoordinateSet pos, @NotNull Type slot, @NotNull Item item, int count) {
        this.pos = pos;
        this.slot = slot;
        this.item = item;
        this.count = count;

        assertSlot(slot);

        assertStandalone(item.getItemType());
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "replaceitem block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + slot + " " + item + (count != 1 ? " " + count : ""));
    }
}
