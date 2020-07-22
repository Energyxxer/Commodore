package com.energyxxer.commodore.functionlogic.commands.setworldspawn;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.rotation.RotationUnit;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SetWorldSpawnCommand implements Command {

    @Nullable
    private final CoordinateSet pos;
    @Nullable
    private final RotationUnit angle;

    public SetWorldSpawnCommand() {
        this(new CoordinateSet());
    }

    public SetWorldSpawnCommand(@Nullable CoordinateSet pos) {
        this(pos, null);
    }

    public SetWorldSpawnCommand(@Nullable CoordinateSet pos, @Nullable RotationUnit angle) {
        this.pos = pos;
        this.angle = angle;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setworldspawn" + (pos != null && (pos.isSignificant() || angle != null) ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) + (angle != null ? " " + angle.toString() : "") : ""));
    }

    @Override
    public void assertAvailable() {
        if(angle != null) {
            VersionFeatureManager.assertEnabled("command.spawnpoint.angle");
        }
    }
}
