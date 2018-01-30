package com.energyxxer.commodore.score;

import com.energyxxer.commodore.inspection.CommandEmbeddable;

import java.util.Collection;

public interface ScoreHolder extends CommandEmbeddable {
    String getReference();

    Collection<MacroScoreHolder> getMacroHolders();
}
