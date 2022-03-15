package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariable;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class SortArgument implements SelectorArgument {

    /**
     * Describes the different sorting modes a selector can utilize to sort its targeted entities.
     * */
    public enum SortMode {
        /**
         * Specifies that the targeted entities will be listed from nearest first to farthest last. The execution
         * location and dimension are used to calculate this distance.
         * */
        NEAREST(true),
        /**
         * Specifies that the targeted entities will be listed from furthest first to nearest last. The execution
         * location and dimension are used to calculate this distance.
         * */
        FURTHEST(true),
        /**
         * Specifies that the targeted entities will be listed in a random order.
         * */
        RANDOM(false),
        /**
         * Specifies that the targeted entities' order will not be modified, and instead will be returned in the order
         * they appear in the game's memory.
         * */
        ARBITRARY(false);

        /**
         * Whether this sorting mode depends on the execution location.
         * */
        private final boolean positionSensitive;

        /**
         * Creates a sorting mode with the given position-sensitive flag.
         *
         * @param positionSensitive Whether this sorting mode requires knowledge of the execution location to be used.
         * */
        SortMode(boolean positionSensitive) {
            this.positionSensitive = positionSensitive;
        }

        /**
         * Checks whether this sorting mode is dependent on the execution location.
         *
         * @return <code>true</code> if the execution location is used for this sorting mode,
         * <code>false</code> otherwise.
         * */
        public boolean isPositionSensitive() {
            return positionSensitive;
        }

    }

    @NotNull
    private final SortMode mode;

    public SortArgument(@NotNull SortMode mode) {
        this.mode = mode;
    }

    @NotNull
    public SortMode getSortMode() {
        return mode;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "sort=" + mode.toString().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public SortArgument clone() {
        return new SortArgument(mode);
    }

    @NotNull
    @Override
    public String getKey() {
        return "sort";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return (mode.isPositionSensitive() ? new ExecutionVariableMap(ExecutionVariable.X, ExecutionVariable.Y, ExecutionVariable.Z) : null);
    }

    @Override
    public void assertCompatibleWith(@NotNull Selector selector) {
        if(selector.getBase().getSorting().isEnforced()) throw new CommodoreException(CommodoreException.Source.SELECTOR_ERROR, "Sort is not applicable for the " + selector.getBase() + " selector type", selector);
        SelectorArgument.super.assertCompatibleWith(selector);
    }

    @Override
    public String toString() {
        return getArgumentString();
    }
}
