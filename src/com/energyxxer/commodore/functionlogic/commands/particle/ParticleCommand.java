package com.energyxxer.commodore.functionlogic.commands.particle;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.commands.Command;
import com.energyxxer.commodore.functionlogic.coordinates.Coordinate;
import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.util.Delta;
import com.energyxxer.commodore.util.Particle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ParticleCommand implements Command {
    @NotNull
    private final Particle particle;
    @Nullable
    private final CoordinateSet position;
    @Nullable
    private final Delta delta;
    private final double speed;
    private final int count;
    private final boolean force;
    @Nullable
    private final Entity viewers;

    public ParticleCommand(@NotNull Particle particle) {
        this(particle, null);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position) {
        this(particle, position, null);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position, @Nullable Delta delta) {
        this(particle, position, delta, 0);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position, @Nullable Delta delta, double speed) {
        this(particle, position, delta, speed, 1);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position, @Nullable Delta delta, double speed, int count) {
        this(particle, position, delta, speed, count, false);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position, @Nullable Delta delta, double speed, int count, boolean force) {
        this(particle, position, delta, speed, count, force, null);
    }

    public ParticleCommand(@NotNull Particle particle, @Nullable CoordinateSet position, @Nullable Delta delta, double speed, int count, boolean force, @Nullable Entity viewers) {
        this.particle = particle;
        this.position = (position != null && position.isSignificant()) ? position : null;
        this.delta = (delta != null && (delta.x != 0 || delta.y != 0 || delta.z != 0)) ? delta : null;
        this.speed = speed;
        if(speed < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Speed must not be less than 0.0, found " + speed, speed, "SPEED");
        this.count = count;
        if(count < 0) throw new CommodoreException(CommodoreException.Source.NUMBER_LIMIT_ERROR, "Count must not be less than 0.0, found " + count, count, "COUNT");
        this.force = force;
        this.viewers = viewers;
        if(viewers != null) viewers.assertPlayer();
    }

    @Override @NotNull
    public CommandResolution resolveCommand(ExecutionContext execContext) {
        StringBuilder sb = new StringBuilder();
        if(viewers != null) sb.insert(0, viewers.toString());
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
            sb.insert(0, CommandUtils.numberToPlainString(speed));
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
        if(viewers != null) return new CommandResolution(execContext, sb.toString().trim());
        return new CommandResolution(execContext, sb.toString().trim());
    }

}
