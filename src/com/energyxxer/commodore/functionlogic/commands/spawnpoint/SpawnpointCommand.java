package com.energyxxer.commodore.functionlogic.commands.spawnpoint;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SpawnpointCommand implements Command {

    private final Entity player;
    private final CoordinateSet pos;

    public SpawnpointCommand() {
        this(null);
    }

    public SpawnpointCommand(Entity player) {
        this(player, new CoordinateSet());
    }

    public SpawnpointCommand(Entity player, CoordinateSet pos) {
        this.player = player;
        this.pos = pos;

        if(player != null) player.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return player != null ? new CommandResolution(execContext, "spawnpoint \be0" + (pos != null && pos.isSignificant() ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) : ""), player) :
                new CommandResolution(execContext, "spawnpoint");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return player != null ? new ArrayList<>(player.getScoreboardAccesses()) : Collections.emptyList();
    }
}
