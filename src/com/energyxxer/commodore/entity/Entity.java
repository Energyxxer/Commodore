package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.commands.execute.CommandExecutor;
import com.energyxxer.commodore.score.ScoreHolder;
import com.energyxxer.commodore.selector.Selector;

public interface Entity extends CommandExecutor, ScoreHolder {
    Selector getSelector();

    default Selector getSelectorAs(Entity executor) {
        if (executor == this) return new Selector(Selector.BaseSelector.SENDER);
        else return getSelector();
    }

    @Override
    default String getReference() {
        return getSelector().toString();
    }
}
