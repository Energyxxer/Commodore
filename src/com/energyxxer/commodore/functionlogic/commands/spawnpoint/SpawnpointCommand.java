package com.energyxxer.commodore.functionlogic.commands.spawnpoint;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.rotation.RotationUnit;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnpointCommand implements Command {

    @Nullable
    private final Entity player;
    @Nullable
    private final CoordinateSet pos;
    @Nullable
    private final RotationUnit angle;

    public SpawnpointCommand() {
        this(null);
    }

    public SpawnpointCommand(@Nullable Entity player) {
        this(player, new CoordinateSet());
    }

    public SpawnpointCommand(@Nullable Entity player, @Nullable CoordinateSet pos) {
        this(player, pos, null);
    }

    public SpawnpointCommand(@Nullable Entity player, @Nullable CoordinateSet pos, @Nullable RotationUnit angle) {
        this.player = player;
        this.pos = pos;

        if(player != null) {
            player.assertPlayer();
            player.assertEntityFriendly();
        }

        this.angle = angle;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "spawnpoint" + (player != null ? " " + player : "") + (pos != null && (pos.isSignificant() || angle != null) ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + (angle != null ? " " + angle.toString() : "") : ""));
    }

    @Override
    public void assertAvailable() {
        if(player != null) player.assertAvailable();
        if(angle != null) {
            VersionFeatureManager.assertEnabled("command.spawnpoint.angle");
        }
    }
}
