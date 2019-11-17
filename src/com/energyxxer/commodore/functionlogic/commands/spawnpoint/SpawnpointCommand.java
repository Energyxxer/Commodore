package com.energyxxer.commodore.functionlogic.commands.spawnpoint;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnpointCommand implements Command {

    @Nullable
    private final Entity player;
    @Nullable
    private final CoordinateSet pos;

    public SpawnpointCommand() {
        this(null);
    }

    public SpawnpointCommand(@Nullable Entity player) {
        this(player, new CoordinateSet());
    }

    public SpawnpointCommand(@Nullable Entity player, @Nullable CoordinateSet pos) {
        this.player = player;
        this.pos = pos;

        if(player != null) {
            player.assertPlayer();
            player.assertEntityFriendly();
        }
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return player != null ? new CommandResolution(execContext, "spawnpoint " + player + (pos != null && pos.isSignificant() ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) : "")) :
                new CommandResolution(execContext, "spawnpoint");
    }

    @Override
    public void assertAvailable() {
        if(player != null) player.assertAvailable();
    }
}
