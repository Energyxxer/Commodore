package com.energyxxer.commodore.functionlogic.commands.teleport;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;

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
