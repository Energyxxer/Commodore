package com.energyxxer.commodore.commands.spawnpoint;

import com.energyxxer.commodore.commands.Command;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class SpawnpointCommand implements Command {

    private final Entity player;
    private final CoordinateSet pos;

    public SpawnpointCommand(Entity player, CoordinateSet pos) {
        this.player = player;
        this.pos = pos;

        player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spawnpoint \be0 " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS), player);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(player.getScoreboardAccesses());
    }
}
