package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.util.NumberRange;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ScoreArgument implements ComplexSelectorArgument {
    @NotNull
    private final HashMap<@NotNull Objective, @NotNull NumberRange<Integer>> scores = new HashMap<>();

    public ScoreArgument() {
    }

    public void put(@NotNull Objective objective, @NotNull NumberRange<Integer> value) {
        this.scores.put(objective, value);
        value.assertOrdered();
    }

    @NotNull
    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder("scores={");
        Iterator<Map.Entry<Objective, NumberRange<Integer>>> it = this.scores.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Objective, NumberRange<Integer>> entry = it.next();

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
}
