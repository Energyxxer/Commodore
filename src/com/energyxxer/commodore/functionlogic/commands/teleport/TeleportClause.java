package com.energyxxer.commodore.functionlogic.commands.teleport;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public interface TeleportClause {
    @NotNull
    String getRaw();
    @NotNull
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }
    @NotNull
    default Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.emptyList();
    }
}
