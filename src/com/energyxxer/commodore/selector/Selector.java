package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.Objective;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Selector implements Cloneable {

    /*
     * TODO:
     * Actually not a to-do but stuff to keep in mind:
     * limit on @s is invalid
     * */
    public enum BaseSelector {
        ALL_PLAYERS("a", SortArgument.SortType.ARBITRARY, -1, true),
        NEAREST_PLAYER("p", SortArgument.SortType.NEAREST, 1, true),
        RANDOM_PLAYER("r", SortArgument.SortType.RANDOM, 1, true),
        ALL_ENTITIES("e", SortArgument.SortType.ARBITRARY, -1, false),
        SENDER("s", SortArgument.SortType.ARBITRARY, 1, true);

        private final String header;

        private final SortArgument.SortType defaultSort;

        private final int limit;
        private final boolean player;
        BaseSelector(String header, SortArgument.SortType defaultSort, int limit, boolean player) {
            this.header = header;
            this.defaultSort = defaultSort;
            this.limit = limit;
            this.player = player;
        }

        public String getHeader() {
            return header;
        }

        public SortArgument.SortType getDefaultSort() {
            return defaultSort;
        }

        public int getLimit() {
            return limit;
        }

        public boolean isPlayer() {
            return player;
        }

    }
    private final BaseSelector base;
    private final ArrayList<SelectorArgument> args = new ArrayList<>();

    public Selector(BaseSelector base) {
        this.base = base;
    }

    public Selector(BaseSelector base, SelectorArgument... arguments) {
        this(base);
        this.addArguments(arguments);
    }

    public void addArguments(SelectorArgument... arguments) {
        for(SelectorArgument argument : arguments) {
            addArgument(argument);
        }
    }

    public void addArgument(SelectorArgument argument) {
        try {
            if(argument.isCompatibleWith(this)) this.args.add(argument);
        } catch(IllegalArgumentException x) {
            throw new IllegalArgumentException("Could not add '" + argument.getArgumentString() + "' to selector " + this + ": " + x.getMessage());
        }
    }

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

    public BaseSelector getBase() {
        return base;
    }

    private int getLimitArgument() {
        for(SelectorArgument arg : getArgumentsByKey("limit")) {
            return ((LimitArgument) arg).getLimit();
        }
        return -1;
    }

    public int getLimit() {
        int implicitLimit = base.limit;
        int explicitLimit = getLimitArgument();
        if(implicitLimit < 0) return explicitLimit;
        if(explicitLimit < 0) return implicitLimit;
        return Math.min(implicitLimit, explicitLimit);
    }

    public SortArgument.SortType getSorting() {
        for(SelectorArgument arg : getArgumentsByKey("sort")) {
            return ((SortArgument) arg).getSortType();
        }
        return base.defaultSort;
    }

    public Collection<Objective> getObjectivesRead() {
        ArrayList<Objective> objectives = new ArrayList<>();
        for(SelectorArgument arg : getArgumentsByKey("scores")) {
            for(Objective obj : ((ScoreArgument) arg).getObjectivesRead()) {
                if(!objectives.contains(obj)) objectives.add(obj);
            }
        }
        return objectives;
    }

    public Collection<SelectorArgument> getArgumentsByKey(String key) {
        ArrayList<SelectorArgument> args = new ArrayList<>();
        for(SelectorArgument arg : this.args) {
            if(arg.getKey().equals(key)) args.add(arg);
        }
        return args;
    }

    public boolean containsArgumentForKey(String key) {
        for(SelectorArgument arg : args) {
            if(arg.getKey().equals(key)) return true;
        }
        return false;
    }

    public boolean isPlayer() {
        for(SelectorArgument type : getArgumentsByKey("type")) {
            if(!((TypeArgument) type).getType().getName().equals("minecraft:player") || ((TypeArgument) type).isNegated()) {
                return false;
            }
        }

        return base.player;
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
    public Selector clone() {
        Selector copy = new Selector(base);
        args.forEach(a -> copy.addArguments(a.clone()));
        return copy;
    }

    public String toVerboseString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.toString());

        int spaces = 75 - sb.length();
        for(int i = 0; i < spaces; i++) sb.append(' ');
        sb.append(getUsedExecutionVariables());

        return sb.toString();
    }
}

class SelectorArgumentParseResult {
    String raw;
    final SelectorArgument arg;

    public SelectorArgumentParseResult(String raw, SelectorArgument arg) {
        this.raw = raw;
        this.arg = arg;
    }
}