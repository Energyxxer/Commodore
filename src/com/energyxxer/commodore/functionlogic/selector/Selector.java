package com.energyxxer.commodore.functionlogic.selector;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.selector.arguments.*;
import com.energyxxer.commodore.functionlogic.selector.arguments.SortArgument.SortMode;
import com.energyxxer.commodore.util.BaseSelectorProperty;
import com.energyxxer.commodore.util.BaseSelectorProperty.EnforcementType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an entity selector used in-game to target any number of entities, using a mix of base selectors and
 * arguments.
 *
 * @see com.energyxxer.commodore.functionlogic.entity.Entity
 * */
public class Selector implements Entity, Cloneable {
    /**
     * Represents a base selector type with default filtering of entities.
     * */
    public enum BaseSelector {
        /**
         * Command equivalent: @a<br>
         * Targets all logged-in player entities in all dimensions.<br>
         * Default sorting is arbitrary, and can target more than one entity.
         * */
        ALL_PLAYERS("a",
                new BaseSelectorProperty<>(SortMode.ARBITRARY, EnforcementType.DEFAULT),
                new BaseSelectorProperty<>(-1, EnforcementType.DEFAULT),
                new BaseSelectorProperty<>(true, EnforcementType.ENFORCED)),
        /**
         * Command equivalent: @p<br>
         * Targets the nearest logged-in player entity in the current dimension.<br>
         * Default sorting is nearest first, and limited to target only one entity.
         * */
        NEAREST_PLAYER("p",
                new BaseSelectorProperty<>(SortMode.NEAREST, EnforcementType.ENFORCED),
                new BaseSelectorProperty<>(1, EnforcementType.ENFORCED),
                new BaseSelectorProperty<>(true, EnforcementType.ENFORCED)),
        /**
         * Command equivalent: @r<br>
         * Targets a number of entities picked at random.<br>
         * Default sorting is random, by default targets players only and by default limited to 1 entity.
         * */
        RANDOM_PLAYER("r",
                new BaseSelectorProperty<>(SortMode.RANDOM, EnforcementType.ENFORCED),
                new BaseSelectorProperty<>(1, EnforcementType.DEFAULT),
                new BaseSelectorProperty<>(true, EnforcementType.DEFAULT)),
        /**
         * Command equivalent: @e<br>
         * Targets all loaded entities in all dimensions.<br>
         * Default sorting is arbitrary, and can target more than one entity.
         * */
        ALL_ENTITIES("e",
                new BaseSelectorProperty<>(SortMode.ARBITRARY, EnforcementType.DEFAULT),
                new BaseSelectorProperty<>(-1, EnforcementType.DEFAULT),
                new BaseSelectorProperty<>(false, EnforcementType.DEFAULT)),
        /**
         * Command equivalent: @s<br>
         * Targets the currently executing entity, if it exists.<br>
         * Default sorting is arbitrary, and locked to target only one entity.
         * */
        SENDER("s",
                new BaseSelectorProperty<>(SortMode.ARBITRARY, EnforcementType.ENFORCED),
                new BaseSelectorProperty<>(1, EnforcementType.ENFORCED),
                new BaseSelectorProperty<>(false, EnforcementType.DEFAULT));

        /**
         * The sequence of characters following the at (@) symbol that begins a selector of this type.
         * */
        private final String header;

        /**
         * The sorting operation for this selector type, and whether it is enforced or not.
         * */
        private final BaseSelectorProperty<SortMode> sorting;

        /**
         * The limit of entities this selector can target, and whether it is enforced or not.
         * A value of -1 means there is no limit to the number of entities targeted.
         * */
        private final BaseSelectorProperty<Integer> limit;
        /**
         * Whether this selector only targets players by default, and whether that is enforced.
         * */
        private final BaseSelectorProperty<Boolean> player;

        /**
         * Creates a base selector with the given header, sorting operation, limit and player flag.
         *
         * @param header The sequence of characters following the at (@) symbol that begins the selector.
         * @param sorting The sorting operation for this selector type, and whether it is enforced.
         * @param limit The limit of entities this selector can target, and whether it is enforced.
         *              -1 means there isn't a limit to the number of entities targeted.
         * @param player Whether this selector by default only targets player entities, and whether it is enforced.
         * */
        BaseSelector(
                String header,
                BaseSelectorProperty<SortMode> sorting,
                BaseSelectorProperty<Integer> limit,
                BaseSelectorProperty<Boolean> player) {
            this.header = header;
            this.sorting = sorting;
            this.limit = limit;
            this.player = player;
        }

        /**
         * Retrieves this base selector's character sequence following the @ symbol.
         *
         * @return The header for this base selector.
         * */
        public String getHeader() {
            return header;
        }

        /**
         * Retrieves this base selector's sorting mode property.
         *
         * @return The default sorting mode property for this base selector.
         * */
        public BaseSelectorProperty<SortMode> getSorting() {
            return sorting;
        }

        /**
         * Retrieves this base selector's count limit property.
         *
         * @return The limit property for this base selector.
         * */
        public BaseSelectorProperty<Integer> getLimit() {
            return limit;
        }

        /**
         * Retrieves this base selector's player flag property; that is, whether it only targets players on its own.
         *
         * @return <code>true</code> if this selector only targets player entities by default,
         * <code>false</code> otherwise.
         * */
        public BaseSelectorProperty<Boolean> getPlayer() {
            return player;
        }

        /**
         * Retrieves the base selector object corresponding to the given header characters.
         *
         * @param header The character used by the header of the base selector to retrieve.
         * @return The base selector whose header is the given string.
         * */
        public static BaseSelector getForHeader(String header) {
            for(BaseSelector base : values()) {
                if(base.header.equals(header)) return base;
            }
            return null;
        }
    }
    /**
     * The selector type for this selector.
     * */
    @NotNull
    private final BaseSelector base;
    /**
     * The list of arguments for this selector.
     * */
    @NotNull
    private final ArrayList<SelectorArgument> args = new ArrayList<>();

    /**
     * Creates a selector from the given type.
     *
     * @param base The base type of the new selector.
     * */
    public Selector(@NotNull BaseSelector base) {
        this.base = base;
    }

    /**
     * Creates a selector from the  given type and arguments.
     *
     * @param base The base type of the new selector.
     * @param arguments The arguments for this selector.
     * */
    public Selector(@NotNull BaseSelector base, @NotNull SelectorArgument... arguments) {
        this(base);
        this.addArguments(arguments);
    }

    /**
     * Adds the given arguments to the selector.
     *
     * @param arguments The arguments to add to this selector.
     * */
    public void addArguments(@NotNull SelectorArgument... arguments) {
        for(SelectorArgument argument : arguments) {
            addArgument(argument);
        }
    }

    /**
     * Adds the given arguments to the selector.
     *
     * @param arguments The arguments to add to this selector.
     * */
    public void addArguments(@NotNull Collection<@NotNull SelectorArgument> arguments) {
        for(SelectorArgument argument : arguments) {
            addArgument(argument);
        }
    }

    /**
     * Adds the given argument to the selector, merging arguments when possible.
     *
     * @param argument The argument to add to this selector.
     * */
    public void addArgument(@NotNull SelectorArgument argument) {
        argument.assertCompatibleWith(this);
        this.args.add(argument);
    }

    /**
     * Adds the given arguments to the selector, merging arguments when possible.
     *
     * @param arguments The arguments to add to this selector.
     * */
    public void addArgumentsMerging(@NotNull SelectorArgument... arguments) {
        for(SelectorArgument argument : arguments) {
            addArgumentMerging(argument);
        }
    }

    /**
     * Adds the given arguments to the selector, merging arguments when possible.
     *
     * @param arguments The arguments to add to this selector.
     * */
    public void addArgumentsMerging(@NotNull Collection<@NotNull SelectorArgument> arguments) {
        for(SelectorArgument argument : arguments) {
            addArgumentMerging(argument);
        }
    }

    /**
     * Adds the given argument to the selector, merging arguments when possible.
     *
     * @param argument The argument to add to this selector.
     * */
    public void addArgumentMerging(@NotNull SelectorArgument argument) {
        Collection<SelectorArgument> oldArgs = this.getArgumentsByKey(argument.getKey());
        if(!argument.isRepeatable()) {
            this.removeArguments(argument.getClass());
        }
        argument.assertCompatibleWith(this);
        if(argument instanceof ComplexSelectorArgument) {
            boolean hadAny = false;
            for(SelectorArgument original : oldArgs) {
                this.args.remove(original);
                this.args.add(((ComplexSelectorArgument) original).merge(((ComplexSelectorArgument) argument)));
                hadAny = true;
            }
            if(!hadAny) this.args.add(argument);
        } else {
            this.args.add(argument);
        }
    }

    /**
     * Removes all arguments of the provided class from the selector.
     *
     * @param argumentType The class of the arguments to remove from this selector.
     * */
    public void removeArguments(Class<? extends SelectorArgument> argumentType) {
        this.args.removeIf(argumentType::isInstance);
    }

    /**
     * Removes the provided argument from this selector, if it exists.
     *
     * @param argument The argument to remove from this selector.
     * */
    public void removeArgument(SelectorArgument argument) {
        this.args.remove(argument);
    }

    /**
     * Checks if this selector contains an argument of the given type.
     *
     * @param argumentType The argument type to find in this selector.
     *
     * @return Whether this selector contains an argument of the type in the provided parameter.
     * */
    public boolean containsArgument(Class<? extends SelectorArgument> argumentType) {
        for(SelectorArgument arg : args) {
            if(argumentType.isInstance(arg)) return true;
        }
        return false;
    }

    /**
     * Retrieves this selector's base selector.
     *
     * @return The base for this selector.
     * */
    @NotNull
    public BaseSelector getBase() {
        return base;
    }

    /**
     * Retrieves the limit given by this selector's limit argument, if it exists.
     *
     * @return The explicit limit for this selector. If the limit argument doesn't exist, -1 is returned.
     * */
    private int getLimitArgument() {
        for(SelectorArgument arg : getArgumentsByKey("limit")) {
            return ((LimitArgument) arg).getLimit();
        }
        return -1;
    }

    /**
     * Calculates this selector's limit via a combination of the base's default limit and this selector's limit
     * argument.
     *
     * @return The limit of entities this selector can target. A value of -1 means there is no limit to the number of
     * entities.
     * */
    public int getLimit() {
        BaseSelectorProperty<Integer> implicitLimit = base.limit;
        if(implicitLimit.isEnforced()) return implicitLimit.getValue();
        int explicitLimit = getLimitArgument();
        if(implicitLimit.getValue() < 0) return explicitLimit;
        if(explicitLimit < 0) return implicitLimit.getValue();
        return Math.min(implicitLimit.getValue(), explicitLimit);
    }

    /**
     * Retrieves this selector's sorting mode.
     *
     * @return The sorting mode for this selector.
     * */
    @NotNull
    public SortMode getSorting() {
        if(base.sorting.isEnforced()) return base.sorting.getValue();
        for(SelectorArgument arg : getArgumentsByKey("sort")) {
            return ((SortArgument) arg).getSortMode();
        }
        return base.sorting.getValue();
    }

    /**
     * Retrieves the objectives read by this selector.
     *
     * @return A list of objectives found in this selector's 'scores' argument.
     * */
    @NotNull
    public Collection<@NotNull Objective> getObjectivesRead() {
        ArrayList<Objective> objectives = new ArrayList<>();
        for(SelectorArgument arg : getArgumentsByKey("scores")) {
            for(Objective obj : ((ScoreArgument) arg).getObjectivesRead()) {
                if(!objectives.contains(obj)) objectives.add(obj);
            }
        }
        return objectives;
    }

    /**
     * Retrieves a list of selector arguments with the given key.
     *
     * @param key The name of the arguments to retrieve.
     *
     * @return A list of selector arguments whose key are the same as the one given.
     * */
    @NotNull
    public Collection<@NotNull SelectorArgument> getArgumentsByKey(String key) {
        ArrayList<SelectorArgument> args = new ArrayList<>();
        for(SelectorArgument arg : this.args) {
            if(arg.getKey().equals(key)) args.add(arg);
        }
        return args;
    }

    /**
     * Checks if, among this selector's arguments, there is at least one argument with the given key.
     *
     * @param key The name of the arguments whose presence should be checked.
     *
     * @return <code>true</code> if at least one of this selector's arguments is of the given key,
     * <code>false</code> otherwise.
     * */
    public boolean containsArgumentForKey(@NotNull String key) {
        for(SelectorArgument arg : args) {
            if(arg.getKey().equals(key)) return true;
        }
        return false;
    }

    /**
     * Creates and retrieves a list of all arguments currently in the selector.
     *
     * @return A list with all the arguments in this selector. Changes made to this list will not apply to this selector.
     * */
    public List<SelectorArgument> getAllArguments() {
        return new ArrayList<>(args);
    }

    @Override
    public boolean isUnknownType() {
        return base == BaseSelector.SENDER;
    }

    @Override
    public boolean isPlayer() {
        if(base.player.isEnforced()) return base.player.getValue();
        for(SelectorArgument type : getArgumentsByKey("type")) {
            boolean argPlayer = ((TypeArgument) type).getType().toString().equals("minecraft:player");
            if(!((TypeArgument) type).isNegated()) {
                return argPlayer;
            }
        }

        return base.getPlayer().getValue();
    }

    @Override
    public void assertGameProfile(String causeKey) {
        assertPlayer(causeKey);
        if(base == BaseSelector.SENDER && args.isEmpty()) {
            throw new CommodoreException(CommodoreException.Source.ENTITY_ERROR, "Provided entity '" + this + "' is not a valid game profile (don't look at me, it's mojang's fault!)", this, causeKey);
        }
    }

    public ExecutionVariableMap getUsedExecutionVariables() {
        ExecutionVariableMap usedVariables = new ExecutionVariableMap();

        if(getSorting().isPositionSensitive()) {
            usedVariables.setUsed(ExecutionVariable.X);
            usedVariables.setUsed(ExecutionVariable.Y);
            usedVariables.setUsed(ExecutionVariable.Z);
        }
        if(base == BaseSelector.SENDER) usedVariables.setUsed(ExecutionVariable.SENDER);

        for(SelectorArgument arg : args) {
            usedVariables.merge(arg.getUsedExecutionVariables());
        }

        //Remove execution variables for overwritten coordinates
        if(containsArgumentForKey("x")) usedVariables.setUsed(ExecutionVariable.X, false);
        if(containsArgumentForKey("y")) usedVariables.setUsed(ExecutionVariable.Y, false);
        if(containsArgumentForKey("z")) usedVariables.setUsed(ExecutionVariable.Z, false);

        return usedVariables;
    }

    @Override
    public Entity limitToOne() {
        Selector newSelector = this.clone();
        if(newSelector.getLimit() != 1) {
            newSelector.removeArguments(LimitArgument.class);
            newSelector.addArgument(new LimitArgument(1));
        }
        return newSelector;
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("@");
        sb.append(base.getHeader());
        if(!args.isEmpty()) {
            sb.append('[');
            Iterator<SelectorArgument> it = args.iterator();
            while(it.hasNext()) {
                SelectorArgument arg = it.next();
                sb.append(arg.getArgumentString());
                if(it.hasNext()) sb.append(',');
            }
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public Selector clone() {
        Selector copy = new Selector(base);
        args.forEach(a -> copy.addArguments(a.clone()));
        return copy;
    }
}
