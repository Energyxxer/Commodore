package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.item.Item;

public class GiveCommand implements Command {

    private Entity player;
    private Item item;
    private int count;

    public GiveCommand(Entity player, Item item) {
        this(player, item, 1);
    }

    public GiveCommand(Entity player, Item item, int count) {
        this.player = player;
        this.item = item;
        this.count = count;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "give " + player.getSelectorAs(sender) + " " + item + (count != 1 ? " " + count : "");
    }
}
