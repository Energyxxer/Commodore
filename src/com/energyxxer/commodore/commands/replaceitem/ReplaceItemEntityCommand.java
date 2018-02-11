package com.energyxxer.commodore.commands.replaceitem;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ReplaceItemEntityCommand extends ReplaceItemCommand {

    private final Entity entity;
    private final String slot;
    private final Item item;
    private final int count;

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

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "replaceitem entity \be0 " + slot + " " + item + (count != 1 ? " " + count : ""), entity);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
