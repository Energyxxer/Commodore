package com.energyxxer.commodore.score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class LocalScore {
    private final Objective objective;
    private final ScoreHolder holder;

    public LocalScore(Objective objective, ScoreHolder holder) {
        this.objective = objective;
        this.holder = holder;
    }

    public Objective getObjective() {
        return objective;
    }

    public ScoreHolder getHolder() {
        return holder;
    }

    public Collection<MacroScore> getMacroScores() {
        if(holder == null) return Collections.singletonList(new MacroScore(null, objective));
        ArrayList<MacroScore> list = new ArrayList<>();
        holder.getMacroHolders().forEach(h -> list.add(new MacroScore(h, objective)));
        return list;
    }

    @Override
    public String toString() {
        return "{" +
                "objective=" + objective +
                ", holder=" + holder +
                '}';
    }
}
