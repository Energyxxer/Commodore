package com.energyxxer.commodore.commands.execute;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public interface ExecuteModifier {
    SubCommandResult getSubCommand(Entity sender);

    @NotNull
    default Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    boolean isIdempotent();

    boolean isSignificant();

    boolean isAbsolute();
}
