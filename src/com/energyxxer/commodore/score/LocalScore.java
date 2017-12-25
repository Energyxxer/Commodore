package com.energyxxer.commodore.score;

import java.util.ArrayList;

public class LocalScore {
    private Objective objective;
    private ScoreHolder holder;

    public LocalScore(Objective objective, ScoreHolder holder) {
        this.objective = objective;
        this.holder = holder;

        objective.getParent().registerLocalScore(this);
    }

    public Objective getObjective() {
        return objective;
    }

    public ScoreHolder getHolder() {
        return holder;
    }

    public ArrayList<MacroScore> getMacroScores() {
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
