package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariable;
import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.Objective;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
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

    @NotNull
    public static Selector parse(String str) {
        str = str.trim();
        if(!str.startsWith("@")) throw new IllegalArgumentException("Not a selector");
        BaseSelector base = null;
        for(BaseSelector b : BaseSelector.values()) {
            if(str.substring(1).startsWith(b.header)) {
                base = b;
                break;
            }
        }
        if(base == null) throw new IllegalArgumentException("Unknown selector header");
        Selector sel = new Selector(base);
        str = str.substring(1+base.header.length());
        if(str.length() == 0) return sel;
        if(!str.startsWith("[") || !str.endsWith("]")) throw new IllegalArgumentException("Malformed selector parameters");
        str = str.substring(1, str.length()-1);

        while(str.length() > 0) {
            SelectorArgumentParseResult nextArgument = parseArgument(str);
            str = str.substring(nextArgument.raw.length());
            if(str.startsWith(",")) {
                str = str.substring(1);
            } else if(str.length() > 0) throw new IllegalArgumentException("Expected , at: " + str);
            if(nextArgument.arg != null) {
                sel.addArguments(nextArgument.arg);
            }
        }

        return sel;
    }

    private static SelectorArgumentParseResult parseArgument(String str) {
        String key = parseKey(str);
        str = str.substring(key.length());

        if(!str.startsWith("=")) throw new IllegalArgumentException("Expected key-value separator at: " + str);
        str = str.substring(1);

        SelectorArgumentParseResult result = delegateParseArgument(key, str);
        result.raw = key + "=" + result.raw;

        return result;
    }

    private static SelectorArgumentParseResult delegateParseArgument(String key, String str) {
        switch(key) {
            case "advancement": return AdvancementArgument.parse(str);
            case "distance": return DistanceArgument.parse(str);
            case "dx": return DXArgument.parse(str);
            case "dy": return DYArgument.parse(str);
            case "dz": return DZArgument.parse(str);
            case "gamemode": return GamemodeArgument.parse(str);
            case "level": return LevelArgument.parse(str);
            case "limit": return LimitArgument.parse(str);
            case "name": return NameArgument.parse(str);
            case "nbt": return NBTArgument.parse(str);
            case "x_rotation": return PitchArgument.parse(str);
            case "y_rotation": return YawArgument.parse(str);
            case "scores": return ScoreArgument.parse(str);
            case "sort": return SortArgument.parse(str);
            case "tag": return TagArgument.parse(str);
            case "type": return TypeArgument.parse(str);
            case "x": return XArgument.parse(str);
            case "y": return YArgument.parse(str);
            case "z": return ZArgument.parse(str);
        }
        throw new IllegalArgumentException("Invalid argument type '" + key + "'");
    }

    private static String parseKey(String str) {
        String[] delimiters = new String[] {"\"", "'"};
        for(String delimiter : delimiters) {
            if(str.startsWith(delimiter)) {
                if(str.endsWith(delimiter)) {
                    str = str.substring(1,str.length()-1);

                    StringBuilder sb = new StringBuilder();

                    boolean escaped = false;
                    for(char c : str.toCharArray()) {
                        if(escaped) {
                            switch(c) {
                                case 'n': {
                                    sb.append('\n');
                                    break;
                                }
                                case 'b': {
                                    sb.append('\b');
                                    break;
                                }
                                case 'r': {
                                    sb.append('\r');
                                    break;
                                }
                                default: {
                                    sb.append(c);
                                }
                            }
                            escaped = false;
                        } else if(c == '\\') {
                            escaped = true;
                        } else sb.append(c);
                    }

                    return sb.toString();

                } else throw new IllegalArgumentException("Unclosed string at: " + str);
            }
        }
        //No delimiter
        for(int i = 0; i < str.length(); i++) {
            if(!"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-_0123456789".contains("" + str.charAt(i))) {
                return str.substring(0,i);
            }
        }
        return str;
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
    SelectorArgument arg;

    public SelectorArgumentParseResult(String raw, SelectorArgument arg) {
        this.raw = raw;
        this.arg = arg;
    }
}