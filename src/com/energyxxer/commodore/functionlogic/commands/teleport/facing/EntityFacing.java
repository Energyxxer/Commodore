package com.energyxxer.commodore.functionlogic.commands.teleport.facing;

import com.energyxxer.commodore.functionlogic.commands.execute.EntityAnchor;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;

import java.util.Collection;
import java.util.Collections;

public class EntityFacing implements TeleportFacing {
    private final Entity entity;
    private final EntityAnchor anchor;

    public EntityFacing(Entity entity) {
        this(entity, EntityAnchor.FEET);
    }

    public EntityFacing(Entity entity, EntityAnchor anchor) {
        this.entity = entity;
        this.anchor = anchor;
    }

    @Override
    public String getRaw() {
        return "facing entity \be# " + anchor.toString().toLowerCase();
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return entity.getScoreboardAccesses();
    }

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(entity);
    }
}
