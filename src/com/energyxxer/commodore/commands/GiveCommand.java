package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

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

        if(!item.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual items");
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "give " + player.getSelectorAs(sender) + " " + item + (count != 1 ? " " + count : "");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
