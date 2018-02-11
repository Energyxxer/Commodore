package com.energyxxer.commodore.commands.teleport;

import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.rotation.Rotation;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TeleportToCoordsCommand extends TeleportCommand {
    private final Entity entity;
    private final CoordinateSet pos;
    private final Rotation rotation;

    public TeleportToCoordsCommand(Entity entity, CoordinateSet pos) {
        this(entity, pos, null);
    }

    public TeleportToCoordsCommand(Entity entity, CoordinateSet pos, Rotation rotation) {
        this.entity = entity;
        this.pos = pos;
        this.rotation = rotation;
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        return new CommandResolution(execContext, "tp \be0 " + pos.getAs(Coordinate.DisplayMode.ENTITY_POS) + (rotation != null ? " " + rotation : ""), entity);
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
