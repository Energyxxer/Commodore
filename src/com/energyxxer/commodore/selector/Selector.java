package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.score.Objective;

import java.util.*;

public class Selector implements Cloneable {
    public enum BaseSelector {
        ALL_PLAYERS("a", SortArgument.SortType.NEAREST, -1, true),
        NEAREST_PLAYER("p", SortArgument.SortType.NEAREST, 1, true),
        RANDOM_PLAYER("r", SortArgument.SortType.RANDOM, 1, true),
        ALL_ENTITIES("e", SortArgument.SortType.ARBITRARY, -1, false),
        SENDER("s", SortArgument.SortType.ARBITRARY, 1, true);

        private String header;

        private SortArgument.SortType defaultSort;
        private int limit;
        private boolean player;

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
    private BaseSelector base;

    private ArrayList<SelectorArgument> args = new ArrayList<>();

    public Selector(BaseSelector base) {
        this.base = base;
    }

    public Selector(BaseSelector base, SelectorArgument... arguments) {
        this(base);
        this.addArguments(arguments);
    }

    public void addArguments(SelectorArgument... arguments) {
        this.args.addAll(Arrays.asList(arguments));
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

    private int getLimitArgument() {
        for(SelectorArgument arg : args) {
            if(arg instanceof LimitArgument) return ((LimitArgument) arg).getLimit();
        }
        return -1;
    }

    private List<TypeArgument> getTypeArguments() {
        ArrayList<TypeArgument> typeArgs = new ArrayList<>();
        for(SelectorArgument arg : args) {
            if(arg instanceof TypeArgument) typeArgs.add((TypeArgument) arg);
        }
        return typeArgs;
    }

    public int getLimit() {
        int implicitLimit = base.limit;
        int explicitLimit = getLimitArgument();
        if(implicitLimit < 0) return explicitLimit;
        if(explicitLimit < 0) return implicitLimit;
        return Math.min(implicitLimit, explicitLimit);
    }

    public Collection<Objective> getObjectivesRead() {
        ArrayList<Objective> objectives = new ArrayList<>();
        for(SelectorArgument arg : args) {
            if(arg instanceof ScoreArgument) {
                for(Objective obj : ((ScoreArgument) arg).getObjectivesRead()) {
                    if(!objectives.contains(obj)) objectives.add(obj);
                }
            }
        }
        return objectives;
    }

    public boolean isPlayer() {
        List<TypeArgument> typeArgs = getTypeArguments();
        boolean player = base.player;

        for(TypeArgument type : typeArgs) {
            if(!type.getType().getName().equals("minecraft:player") || type.isNegated()) {
                return false;
            }
        }

        return base.player;
    }

    @Override
    public Selector clone() {
        Selector copy = new Selector(base);
        args.forEach(a -> copy.addArguments(a.clone()));
        return copy;
    }
}
