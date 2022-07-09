package com.energyxxer.commodore.functionlogic.commands.place;

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

public class PlaceTemplateCommand implements Command {
    @NotNull
    private final Type type;
    @Nullable
    private final CoordinateSet pos;
    @Nullable
    private final Rotation rotation;
    @Nullable
    private final Mirror mirror;

    public PlaceTemplateCommand(@NotNull Type type, @Nullable CoordinateSet pos, @Nullable Rotation rotation, @Nullable Mirror mirror) {
        this.type = type;
        this.pos = pos;
        this.rotation = rotation;
        this.mirror = mirror;

        assertType(type, "template");
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("command.place.template");
    }

    @Override
    public @NotNull CommandResolution resolveCommand(ExecutionContext execContext) {
        ArgumentCommandBuilder acb =
                new ArgumentCommandBuilder("place feature")
                        .append(type.toSafeString())
                        .append(pos, Coordinate.DisplayMode.BLOCK_POS)
                        .append(rotation, Rotation.NONE)
                        .append(mirror, Mirror.NONE);
        return new CommandResolution(execContext, acb.toString());
    }

    public enum Rotation {
        NONE("none"),
        _180("180"),
        CLOCKWISE_90("clockwise_90"),
        COUNTERCLOCKWISE_90("counterclockwise_90");

        private String literal;

        Rotation(String literal) {
            this.literal = literal;
        }

        @Override
        public String toString() {
            return literal;
        }
    }
    public enum Mirror {
        NONE("none"),
        FRONT_BACK("front_back"),
        LEFT_RIGHT("left_right");

        private String literal;

        Mirror(String literal) {
            this.literal = literal;
        }

        @Override
        public String toString() {
            return literal;
        }
    }
}
