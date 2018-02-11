package com.energyxxer.commodore.commands.clear;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

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

        player.assertPlayer();
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "clear \be0 " + item + ((maxCount >= 0) ? " " + maxCount : ""), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
