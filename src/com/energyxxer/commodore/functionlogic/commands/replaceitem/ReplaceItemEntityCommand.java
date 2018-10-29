package com.energyxxer.commodore.functionlogic.commands.replaceitem;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

import static com.energyxxer.commodore.types.TypeAssert.assertStandalone;

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

        assertStandalone(item.getItemType());
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
