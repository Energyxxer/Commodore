package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.rotation.Rotation;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TeleportToCoordsCommand extends TeleportCommand {
    private Entity entity;
    private CoordinateSet pos;
    private Rotation rotation;

    public TeleportToCoordsCommand(Entity entity, CoordinateSet pos) {
        this(entity, pos, null);
    }

    public TeleportToCoordsCommand(Entity entity, CoordinateSet pos, Rotation rotation) {
        this.entity = entity;
        this.pos = pos;
        this.rotation = rotation;
    }

    @Override
    public String getRawCommand(Entity sender) {
        return "tp " + entity.getSelectorAs(sender) + " " + pos + (rotation != null ? " " + rotation : "");
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return new ArrayList<>(entity.getScoreboardAccesses());
    }
}
