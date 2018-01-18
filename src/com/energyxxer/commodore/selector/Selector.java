package com.energyxxer.commodore.selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Selector implements Cloneable {
    public enum BaseSelector {
        ALL_PLAYERS("a", SortArgument.SortType.NEAREST), NEAREST_PLAYER("p", SortArgument.SortType.NEAREST, 1), RANDOM_PLAYER("r", SortArgument.SortType.RANDOM), ALL_ENTITIES("e", SortArgument.SortType.ARBITRARY), SENDER("s", SortArgument.SortType.ARBITRARY, 1);

        private String header;
        private SortArgument.SortType defaultSort;
        private int limit;

        BaseSelector(String header, SortArgument.SortType defaultSort) {
            this(header, defaultSort, 0);
        }

        BaseSelector(String header, SortArgument.SortType defaultSort, int limit) {
            this.header = header;
            this.defaultSort = defaultSort;
            this.limit = limit;
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

    public int getLimit() {
        int implicitLimit = base.limit;
        int explicitLimit = getLimitArgument();
        if(implicitLimit < 0) return explicitLimit;
        if(explicitLimit < 0) return implicitLimit;
        return Math.min(implicitLimit, explicitLimit);
    }

    @Override
    public Selector clone() {
        Selector copy = new Selector(base);
        args.forEach(a -> copy.addArguments(a.clone()));
        return copy;
    }
}
