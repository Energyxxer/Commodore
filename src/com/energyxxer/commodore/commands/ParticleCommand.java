package com.energyxxer.commodore.commands;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.coordinates.Coordinate;
import com.energyxxer.commodore.coordinates.CoordinateSet;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.inspection.CommandResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.particles.Particle;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.util.Delta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ParticleCommand implements Command {
    private Particle particle;
    private CoordinateSet position;
    private Delta delta;
    private double speed;
    private int count;
    private boolean force;
    private Entity viewers;

    public ParticleCommand(Particle particle) {
        this(particle, null);
    }

    public ParticleCommand(Particle particle, CoordinateSet position) {
        this(particle, position, null);
    }

    public ParticleCommand(Particle particle, CoordinateSet position, Delta delta) {
        this(particle, position, delta, 0);
    }

    public ParticleCommand(Particle particle, CoordinateSet position, Delta delta, double speed) {
        this(particle, position, delta, speed, 1);
    }

    public ParticleCommand(Particle particle, CoordinateSet position, Delta delta, double speed, int count) {
        this(particle, position, delta, speed, count, false);
    }

    public ParticleCommand(Particle particle, CoordinateSet position, Delta delta, double speed, int count, boolean force) {
        this(particle, position, delta, speed, count, force, null);
    }

    public ParticleCommand(Particle particle, CoordinateSet position, Delta delta, double speed, int count, boolean force, Entity viewers) {
        this.particle = particle;
        if(position != null && position.isSignificant()) this.position = position;
        if(delta != null && (delta.x != 0 || delta.y != 0 || delta.z != 0)) this.delta = delta;
        this.speed = speed;
        this.count = count;
        this.force = force;
        this.viewers = viewers;
        if(viewers != null && !viewers.isPlayer()) throw new IllegalArgumentException("Provided viewer entity '" + viewers + "' includes non-player entities, expected only players");
    }

    @Override
    public String getRawCommand(Entity sender) {
        StringBuilder sb = new StringBuilder();
        if(viewers != null) sb.insert(0, viewers.getSelectorAs(sender));
        if(force) {
            sb.insert(0, "force ");
        } else if(sb.length() > 0) {
            sb.insert(0, "normal ");
        }
        if(count != 1 || sb.length() > 0 || speed != 0 || delta != null) {
            sb.insert(0, ' ');
            sb.insert(0, count);
        }
        if(speed != 0 || sb.length() > 0 || delta != null) {
            sb.insert(0, ' ');
            sb.insert(0, CommandUtils.toString(speed));
        }
        if(delta != null || sb.length() > 0) {
            sb.insert(0, ' ');
            if(delta == null) sb.insert(0, "0 0 0");
            else sb.insert(0, delta);
        }
        if(position != null || sb.length() > 0) {
            sb.insert(0, ' ');
            if(position == null) sb.insert(0, "~ ~ ~");
            else sb.insert(0, position.getAs(Coordinate.DisplayMode.ENTITY_POS));
        }
        sb.insert(0, ' ');
        sb.insert(0, particle);
        sb.insert(0, "particle ");
        return sb.toString().trim();
    }

    @Override
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder();
        if(viewers != null) sb.insert(0, "\be0");
        if(force) {
            sb.insert(0, "force ");
        } else if(sb.length() > 0) {
            sb.insert(0, "normal ");
        }
        if(count != 1 || sb.length() > 0 || speed != 0 || delta != null) {
            sb.insert(0, ' ');
            sb.insert(0, count);
        }
        if(speed != 0 || sb.length() > 0 || delta != null) {
            sb.insert(0, ' ');
            sb.insert(0, CommandUtils.toString(speed));
        }
        if(delta != null || sb.length() > 0) {
            sb.insert(0, ' ');
            if(delta == null) sb.insert(0, "0 0 0");
            else sb.insert(0, delta);
        }
        if(position != null || sb.length() > 0) {
            sb.insert(0, ' ');
            if(position == null) sb.insert(0, "~ ~ ~");
            else sb.insert(0, position.getAs(Coordinate.DisplayMode.ENTITY_POS));
        }
        sb.insert(0, ' ');
        sb.insert(0, particle);
        sb.insert(0, "particle ");
        if(viewers != null) return new CommandResolution(execContext, sb.toString().trim(), viewers);
        return new CommandResolution(execContext, sb.toString().trim());
    }

    @Override @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return (viewers != null) ? new ArrayList<>(viewers.getScoreboardAccesses()) : Collections.emptyList();
    }
}
