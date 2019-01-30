package com.energyxxer.commodore.functionlogic.entity;

import com.energyxxer.commodore.CommodoreException;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an entity or entities that can be affected by a command. This entity can then specify what execute
 * modifiers and selector to use in order to target this entity fom a certain execution context on a per-command basis.
 * <br>
 *     This does <b>not</b> represent a particular entity selector which can change the targeted entity over time;
 *     instead, this represents a non-changing entity.
 * */
public interface Entity extends Cloneable {

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
     * Checks whether the type of the entities represented by this object cannot be known just from this object's values.
     * An example of this is the @s selector, where @s could mean any entity type, even if it only returns one entity.
     *
     * Should return false if it expected to return more than one entity type.
     *
     * @return <code>true</code> if this entity represents a single unknown entity type, <code>false</code> otherwise.
     * */
    boolean isUnknownType();

    /**
     * Checks whether all the entities represented by this object are guaranteed to be of type
     * <code>minecraft:player</code>.
     *
     * @return <code>true</code> if this entity represents only players, <code>false</code> otherwise.
     * */
    boolean isPlayer();

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     * The thrown exception's cause key will be <code>"PLAYER"</code>.
     *
     * @throws CommodoreException If this entity doesn't represent only players.
     * */
    default void assertPlayer() {
        assertPlayer("PLAYER");
    }

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     *
     * @param causeKey Key to attach to the CommodoreException thrown by this method.
     *
     * @throws CommodoreException If this entity doesn't represent only players.
     * */
    default void assertPlayer(String causeKey) {
        if(!isUnknownType() && !isPlayer()) throw new CommodoreException(CommodoreException.Source.ENTITY_ERROR, "Provided entity '" + this + "' includes non-player entities, expected only players", this, causeKey);
    }

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     * The thrown exception's cause key will be <code>"ENTITY"</code>.
     *
     * @throws IllegalArgumentException If this entity doesn't represent only players.
     * */
    default void assertSingle() {
        assertSingle("ENTITY");
    }

    /**
     * Throws an exception if this entity may not represent entities of type <code>minecraft:player</code>.
     *
     * @param causeKey Key to attach to the CommodoreException thrown by this method.
     *
     * @throws IllegalArgumentException If this entity doesn't represent only players.
     * */
    default void assertSingle(String causeKey) {
        if(getLimit() != 1) throw new CommodoreException(CommodoreException.Source.ENTITY_ERROR, "Provided entity '" + this + "' includes multiple entities, expected at most one", this, causeKey);
    }

    @NotNull
    String toString();
}
