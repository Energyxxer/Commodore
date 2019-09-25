package com.energyxxer.commodore.functionlogic.commands.clear;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.item.ItemFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClearCommand implements Command {
    @Nullable
    private final Entity player;
    @Nullable
    private final Item item;
    private final int maxCount;

    public ClearCommand() {
        this(null, null);
    }

    public ClearCommand(@Nullable Entity player) {
        this(player, null);
    }

    public ClearCommand(@Nullable Entity player, @Nullable Item item) {
        this(player, item, -1);
    }

    public ClearCommand(@Nullable Entity player, @Nullable Item item, int maxCount) {
        this.player = player;
        this.item = item;
        this.maxCount = maxCount;

        if(player != null) player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return player != null ?
                new CommandResolution(execContext, "clear " + player + ((item != null) ? " " + ItemFormatter.asMatch(item, maxCount >= 0 ? "" + maxCount : null) : "")) :
                new CommandResolution(execContext, "clear");
    }

    @Override
    public void assertAvailable() {
        if (item != null) item.assertAvailable();
        if (player != null) player.assertAvailable();
    }
}
