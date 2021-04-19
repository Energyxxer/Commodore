package com.energyxxer.commodore.functionlogic.commands.loot;

import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.item.ItemFormatter;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class ToolOrHand {

    public static final ToolOrHand MAINHAND = new ToolOrHand("mainhand");
    public static final ToolOrHand OFFHAND = new ToolOrHand("offhand");

    @NotNull
    final String name;

    private ToolOrHand(@NotNull String name) {
        this.name = name;
    }

    public ToolOrHand(@NotNull Type tool) {
        assertItem(tool);
        this.name = tool.toString();
    }

    public ToolOrHand(@NotNull Item item) {
        this.name = ItemFormatter.asSet(item);
    }

    @Override
    public String toString() {
        return name;
    }
}
