package com.energyxxer.commodore.functionlogic.commands.drop;

import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertItem;

public class ToolOrHand {

    public static final ToolOrHand MAINHAND = new ToolOrHand("mainhand");
    public static final ToolOrHand OFFHAND = new ToolOrHand("offhand");

    final String name;

    private ToolOrHand(String name) {
        this.name = name;
    }

    public ToolOrHand(Type tool) {
        assertItem(tool);
        this.name = tool.toString();
    }

    public ToolOrHand(Item item) {
        this.name = item.toString();
    }

    @Override
    public String toString() {
        return name;
    }
}
