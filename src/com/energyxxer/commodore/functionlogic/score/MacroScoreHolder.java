package com.energyxxer.commodore.functionlogic.score;

import java.util.Objects;

/**
 * Serves as a flag that can be added to a ScoreHolder that represents a set or a subset of score holders this score
 * holder belongs to. This is used to optimize scoreboard operations by telling which operations affect what group of
 * score holders.<br>
 *
 * For instance, two entities exist: a <code>player</code> and a <code>villager</code>.
 * If at some point in a function, a scoreboard operation affects all living entities, those entities, (the player and
 * the villager) should be given a MacroScoreHolder relating to that property. If other operations are done over the
 * individual entities, those entities should have their own macro score holder representing themselves (usually done
 * automatically by most {@link ScoreHolder} implementations). As such, if a function performs operation as follows:
 * <ol>
 *     <li>Setting a score to all living entities</li>
 *     <li>Setting another score to the player</li>
 * </ol>
 * ...the second operation will not be removed, as the second operation affects both macro score holders: living
 * entities and players, which are different score holders. If the player were to not have an unique macro score holder
 * for itself, the previous operations would have been assumed to target the same set of entities and as such, the
 * scoreboard access optimizer would remove operation number 1 (given that the scoreboard objective isn't marked as
 * field).<br>
 *
 *     Similarly, if a scoreboard operation were to target all entities in the world, there should be a macro score
 *     holder to represent all entities; otherwise, 'get' operations targeting all entities would not prevent previous
 *     particular entities' 'set' operations being marked as unused:
 *     <ol>
 *         <li>Setting a score to all the entities</li>
 *         <li>Setting a score to all the living entities</li>
 *         <li>Reading a score from all the entities</li>
 *     </ol>
 *     If all entities were not to have a macro score holder representing 'all', then the second operation would be
 *     removed, as the scoreboard optimizer does not know that reading the score from all the entities may also include
 *     reading from all the living entities.<br>
 *
 *         Based on the previous examples of operations with score holders, the macro score holders for each of the
 *         entities are as follows:
 *
 *         <ol>
 *             <li>Player: 'all', 'living', 'player'</li>
 *             <li>Villager: 'all', 'living', 'villager'</li>
 *         </ol>
 * */
public class MacroScoreHolder {
    /**
     * The descriptor for this macro score holder; that is, the string that will be compared against other
     * macro score holders' to evaluate equivalency.
     * */
    private final String descriptor;

    /**
     * Creates a macro score holder with the given descriptor.
     *
     * @param descriptor The descriptor for the new macro score holder.
     * */
    public MacroScoreHolder(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "$[" + descriptor + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MacroScoreHolder that = (MacroScoreHolder) o;
        return Objects.equals(descriptor, that.descriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptor);
    }
}
