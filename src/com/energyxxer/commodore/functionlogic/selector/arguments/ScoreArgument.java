package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.util.NumberRange;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ScoreArgument implements ComplexSelectorArgument {
    @NotNull
    private final HashMap<@NotNull Objective, @NotNull Entry> scores = new HashMap<>();

    public ScoreArgument() {
    }

    public void put(@NotNull Objective objective, @NotNull NumberRange<Integer> value) {
        this.scores.put(objective, new Entry(value));
        value.assertOrdered();
    }

    public void put(@NotNull Objective objective, @NotNull NumberRange<Integer> value, boolean negated) {
        this.scores.put(objective, new Entry(value, negated));
        value.assertOrdered();
    }

    public void put(@NotNull Objective objective, ScoreArgument.Entry entry) {
        this.scores.put(objective, entry);
    }

    @NotNull
    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder("scores={");
        Iterator<Map.Entry<Objective, Entry>> it = this.scores.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Objective, Entry> entry = it.next();

            sb.append(entry.getKey().toString());
            sb.append('=');
            sb.append(entry.getValue());
            if(it.hasNext()) sb.append(',');
        }
        sb.append("}");
        return sb.toString();
    }

    public Collection<Objective> getObjectivesRead() {
        ArrayList<Objective> objectives = new ArrayList<>();
        for(Objective obj : scores.keySet()) {
            if(!objectives.contains(obj)) objectives.add(obj);
        }
        return objectives;
    }

    @Override
    public void assertAvailable() {
        for(Entry entry : scores.values()) {
            entry.assertAvailable();
        }
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @NotNull
    @Override
    public ScoreArgument clone() {
        ScoreArgument copy = new ScoreArgument();
        scores.forEach((k, v) -> copy.put(k, v.clone()));
        return copy;
    }

    @NotNull
    @Override
    public String getKey() {
        return "scores";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public ComplexSelectorArgument merge(ComplexSelectorArgument overwriting) {
        ScoreArgument newArg = this.clone();
        newArg.scores.putAll(((ScoreArgument) overwriting).scores);
        return newArg;
    }

    public static class Entry {
        public NumberRange<Integer> range;
        public boolean negated = false;

        public Entry(NumberRange<Integer> range) {
            this.range = range;
        }

        public Entry(NumberRange<Integer> range, boolean negated) {
            this.range = range;
            this.negated = negated;
        }

        @Override
        public String toString() {
            return (negated ? "!" : "") + range;
        }

        @Override
        public Entry clone() {
            return new Entry(range.clone(), negated);
        }

        public void assertAvailable() {
            if(negated) {
                VersionFeatureManager.assertEnabled("selector.score.negated");
            }
        }
    }
}
