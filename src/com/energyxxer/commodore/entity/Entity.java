package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.inspection.CommandEmbeddableResolution;
import com.energyxxer.commodore.inspection.EntityResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.ScoreHolder;
import com.energyxxer.commodore.score.access.ScoreboardAccess;

import java.util.Collection;

/**
 * Represents an entity or entities that can be affected by a command. This entity can then specify what execute
 * modifiers and selector to use in order to target this entity fom a certain execution context on a per-command basis.
 * <br>
 *     This does <b>not</b> represent a particular entity selector which can change the targeted entity over time;
 *     instead, this represents a non-changing entity.
 * */
public interface Entity extends ScoreHolder, Cloneable, CommandEmbeddable {

    /**
     * Resolves this entity into an {@link EntityResolution}, which contains both the modifiers and the selector
     * needed to target it, based on the given execution context.
     *
     * @param context The context from which this entity should be targeted.
     * @return An {@link EntityResolution} object detailing what execution variables should be changed to target it via
     * its selector.
     * */
    EntityResolution resolveFor(ExecutionContext context);

    @Override
    default CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return resolveFor(execContext).resolveEmbed(execContext);
    }

    /**
     * Adds a {@link MacroScoreHolder} to this entity.
     *
     * @param macro The {@link MacroScoreHolder} to add to this entity.
     * */
    void addMacroHolder(MacroScoreHolder macro);

    /**
     * Retrieves the scoreboard accesses that targeting this entity would require.
     *
     * @return The scoreboard accesses for this entity.
     * */
    Collection<ScoreboardAccess> getScoreboardAccesses();

    /**
     * Retrieves this entity's count limit; that is, how many entities, max, this entity object represents. -1 if
     * indeterminate.
     *
     * @return The limit for this entity.
     * */
    int getLimit();

    /**
     * If possible, creates a new entity object that represents only one of the entities this object represents.
     *
     * @return A new entity object for only one of the entities of this object.
     * */
    Entity limitToOne();

    /**
     * Creates a copy of this entity.
     *
     * @return A clone of this entity object.
     * */
    Entity clone();

    /**
     * Checks whether all the entities represented by this object are guaranteed to be of type
     * <code>minecraft:player</code>.
     *
     * @return <code>true</code> if this entity represents only players, <code>false</code> otherwise.
     * */
    boolean isPlayer();

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     *
     * @throws IllegalArgumentException If this entity doesn't represent only players.
     * */
    default void assertPlayer() {
        if(!isPlayer()) throw new IllegalArgumentException("Provided entity '" + this + "' includes non-player entities, expected only players");
    }

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     *
     * @throws IllegalArgumentException If this entity doesn't represent only players.
     * */
    default void assertSingle() {
        if(getLimit() != 1) throw new IllegalArgumentException("Provided entity '" + this + "' includes multiple entities, expected at most one");
    }
}
