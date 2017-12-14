package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.score.Objective;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ScoreArgument implements SelectorArgument {

    private HashMap<Objective, SelectorNumberArgument<Integer>> scores = new HashMap<>();

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

    @Override
    public boolean isRepeatable() {
        return false;
    }
}
