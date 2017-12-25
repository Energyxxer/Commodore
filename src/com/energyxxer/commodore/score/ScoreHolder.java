package com.energyxxer.commodore.score;

import java.util.Collection;

public interface ScoreHolder {
    String getReference();

    Collection<MacroScoreHolder> getMacroHolders();
}
