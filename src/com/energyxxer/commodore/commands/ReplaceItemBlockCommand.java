package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.item.Item;

public class ReplaceItemBlockCommand extends ReplaceItemCommand {

    private CoordinateSet pos;
    private String slot;
    private Item item;
    private int count;

    public ReplaceItemBlockCommand(CoordinateSet pos, String slot, Item item) {
        this(pos, slot, item, 1);
    }

    public ReplaceItemBlockCommand(CoordinateSet pos, String slot, Item item, int count) {
        this.pos = pos;
        this.slot = slot;
        this.item = item;
        this.count = count;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "replaceitem block " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + " " + slot + " " + item + (count != 1 ? " " + count : "");
    }
}