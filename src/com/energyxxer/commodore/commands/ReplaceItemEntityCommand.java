package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ReplaceItemEntityCommand extends ReplaceItemCommand {

    private Entity entity;
    private String slot;
    private Item item;
    private int count;

    public ReplaceItemEntityCommand(Entity entity, String slot, Item item) {
        this(entity, slot, item, 1);
    }

    public ReplaceItemEntityCommand(Entity entity, String slot, Item item, int count) {
        this.entity = entity;
        this.slot = slot;
        this.item = item;
        this.count = count;

        if(!item.isConcrete()) throw new IllegalArgumentException("Tags aren't allowed here, only actual items");
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "replaceitem entity " + entity.getSelectorAs(sender) + " " + slot + " " + item + (count != 1 ? " " + count : "");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
