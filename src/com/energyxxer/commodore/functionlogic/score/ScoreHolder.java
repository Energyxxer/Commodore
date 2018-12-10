package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Describes an element to which scores can be attached in-game with commands.
 * Classes implementing this interface should specify {@link MacroScoreHolder}s to associate with this holder.
 *
 * @see MacroScoreHolder
 * @see Objective
 * */
public interface ScoreHolder extends CommandEmbeddable {
    /**
     * Retrieves the {@link MacroScoreHolder} that describe this score holder.
     *
     * @return The list of macro score holders that represent this score holder.
     * */
    @NotNull
    Collection<@NotNull MacroScoreHolder> getMacroHolders();
}
