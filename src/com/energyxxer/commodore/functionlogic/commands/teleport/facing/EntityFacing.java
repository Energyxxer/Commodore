package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.commands.execute.EntityAnchor;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class EntityFacing implements TeleportFacing {
    @NotNull
    private final Entity entity;
    @NotNull
    private final EntityAnchor anchor;

    public EntityFacing(@NotNull Entity entity) {
        this(entity, EntityAnchor.FEET);
    }

    public EntityFacing(@NotNull Entity entity, @NotNull EntityAnchor anchor) {
        this.entity = entity;
        this.anchor = anchor;
    }

    @NotNull
    @Override
    public String getRaw() {
        return "facing entity \be# " + anchor.toString().toLowerCase();
    }

    @NotNull
    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity.getScoreboardAccesses();
    }

    @NotNull
    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(entity);
    }
}
