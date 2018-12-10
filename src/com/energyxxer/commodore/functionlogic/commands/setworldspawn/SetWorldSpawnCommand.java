package com.energyxxer.commodore.functionlogic.commands.setworldspawn;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SetWorldSpawnCommand implements Command {

    @Nullable
    private final CoordinateSet pos;

    public SetWorldSpawnCommand() {
        this(new CoordinateSet());
    }

    public SetWorldSpawnCommand(@Nullable CoordinateSet pos) {
        this.pos = pos;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "setworldspawn" + (pos != null && pos.isSignificant() ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) : ""));
    }
}
