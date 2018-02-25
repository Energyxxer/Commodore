package com.energyxxer.commodore.score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalScore that = (LocalScore) o;
        return Objects.equals(objective, that.objective) &&
                Objects.equals(holder, that.holder);
    }

    @Override
    public int hashCode() {

        return Objects.hash(objective, holder);
    }
}
