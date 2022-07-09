package com.energyxxer.commodore.functionlogic.commands.place;

import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class PlaceFeatureCommand implements Command {
    @NotNull
    private final Type type;
    @Nullable
    private final CoordinateSet pos;

    public PlaceFeatureCommand(@NotNull Type type, @Nullable CoordinateSet pos) {
        this.type = type;
        this.pos = pos;

        assertType(type, "worldgen/configured_feature");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.place.feature");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "place feature " + type.toSafeString() + (pos != null ? " " + pos.getAs(Coordinate.DisplayMode.BLOCK_POS) : ""));
    }

}
