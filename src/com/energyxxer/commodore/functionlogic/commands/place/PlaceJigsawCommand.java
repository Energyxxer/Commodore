package com.energyxxer.commodore.functionlogic.commands.place;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.util.ArgumentCommandBuilder;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.energyxxer.commodore.types.TypeAssert.assertType;

public class PlaceJigsawCommand implements Command {
    @NotNull
    private final Type type;
    @NotNull
    private final Type target;
    private final int maxDepth;
    @Nullable
    private final CoordinateSet pos;

    public PlaceJigsawCommand(@NotNull Type type, @NotNull Type target, int maxDepth, @Nullable CoordinateSet pos) {
        this.type = type;
        this.target = target;
        this.maxDepth = maxDepth;
        this.pos = pos;

        assertType(type, "worldgen/template_pool");
        assertType(target, "jigsaw_target");

        if(maxDepth < 1) throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Max depth must not be less than 1, found " + maxDepth, "MAX_DEPTH");
        if(maxDepth > 7) throw new CommodoreException(CommodoreException.Source.FORMAT_ERROR, "Max depth must not be greater than 7, found " + maxDepth, "MAX_DEPTH");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.place.jigsaw");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        ArgumentCommandBuilder acb =
                new ArgumentCommandBuilder("place jigsaw")
                        .append(type.toSafeString())
                        .append(target.toSafeString())
                        .append(maxDepth)
                        .append(pos, Coordinate.DisplayMode.BLOCK_POS);
        return new CommandResolution(execContext, acb.toString());
    }
}
