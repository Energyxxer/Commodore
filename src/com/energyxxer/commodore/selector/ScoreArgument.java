package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.score.Objective;

import java.util.*;

public class ScoreArgument implements SelectorArgument {

    private final HashMap<Objective, SelectorNumberArgument<Integer>> scores = new HashMap<>();

    public ScoreArgument() {
    }

    public void put(Objective objective, SelectorNumberArgument<Integer> value) {
        this.scores.put(objective, value);
    }

    @Override
    public String getArgumentString() {
        StringBuilder sb = new StringBuilder("scores={");
        Iterator<Map.Entry<Objective, SelectorNumberArgument<Integer>>> it = this.scores.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Objective, SelectorNumberArgument<Integer>> entry = it.next();

            sb.append(entry.getKey().getName());
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

    @Override
    public ScoreArgument clone() {
        ScoreArgument copy = new ScoreArgument();
        scores.forEach((k, v) -> copy.put(k, v.clone()));
        return copy;
    }

    @Override
    public String getKey() {
        return "scores";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
