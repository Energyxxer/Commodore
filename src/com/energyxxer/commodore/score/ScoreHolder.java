package com.energyxxer.commodore.score;

import com.energyxxer.commodore.inspection.CommandEmbeddable;

import java.util.Collection;

/**
 * Describes an element to which scores can be attached in-game with commands.
 * Classes implementing this interface should specify {@link MacroScoreHolder}s to associate with this holder.
 *
 * @see MacroScoreHolder
 * @see Objective
 * */
public interface ScoreHolder extends CommandEmbeddable {
    Collection<MacroScoreHolder> getMacroHolders();
}
