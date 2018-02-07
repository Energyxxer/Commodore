package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.commands.execute.CommandExecutor;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.EntityResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.ScoreHolder;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.Selector;

import java.util.Collection;

public interface Entity extends CommandExecutor, ScoreHolder, Cloneable, CommandEmbeddable {
    Selector getSelector();

    default Selector getSelectorAs(Entity executor) {
        if(executor == this) return new Selector(Selector.BaseSelector.SENDER);
        else return getSelector();
    }

    EntityResolution resolveFor(ExecutionContext context);

    void addMacroHolder(MacroScoreHolder macro);

    Collection<ScoreboardAccess> getScoreboardAccesses();

    @Override
    default String getReference() {
        return getSelector().toString();
    }

    int getLimit();

    Entity clone();

    boolean isPlayer();
}
