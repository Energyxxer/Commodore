package com.energyxxer.mctech.entity;

import com.energyxxer.mctech.commands.execute.CommandExecutor;
import com.energyxxer.mctech.score.ScoreHolder;
import com.energyxxer.mctech.selector.Selector;

public interface Entity extends CommandExecutor, ScoreHolder {
    Selector getSelector();
    default Selector getSelectorAs(Entity executor) {
        if(executor == this) return new Selector(Selector.BaseSelector.SENDER);
        else return getSelector();
    }
    @Override
    default String getReference() {
        return getSelector().toString();
    }
}
