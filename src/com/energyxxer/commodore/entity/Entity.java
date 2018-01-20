package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.commands.execute.CommandExecutor;
import com.energyxxer.commodore.score.ScoreHolder;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.Selector;

import java.util.Collection;

public interface Entity extends CommandExecutor, ScoreHolder, Cloneable {
    Selector getSelector();

    default Selector getSelectorAs(Entity executor) {
        if(executor == this) return new Selector(Selector.BaseSelector.SENDER);
        else return getSelector();
    }

    Collection<ScoreboardAccess> getScoreboardAccesses();

    @Override
    default String getReference() {
        return getSelector().toString();
    }

    int getLimit();

    Entity clone();

    boolean isPlayer();
}
