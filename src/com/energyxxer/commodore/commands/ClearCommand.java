package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.item.Item;

public class ClearCommand implements Command {

    private Entity player;
    private Item item;
    private int maxCount;

    public ClearCommand(Entity player, Item item) {
        this(player, item, -1);
    }

    public ClearCommand(Entity player, Item item, int maxCount) {
        this.player = player;
        this.item = item;
        this.maxCount = maxCount;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "clear " + player.getSelectorAs(sender) + " " + item + ((maxCount >= 0) ? " " + maxCount : "");
    }
}
