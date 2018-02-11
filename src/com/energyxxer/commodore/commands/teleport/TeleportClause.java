package com.energyxxer.commodore.commands.teleport;

import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.Collection;
import java.util.Collections;

public interface TeleportClause {
    String getRaw();
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }
    default Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.emptyList();
    }
}
