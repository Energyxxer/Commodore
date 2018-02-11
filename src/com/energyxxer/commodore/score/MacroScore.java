package com.energyxxer.commodore.score;

public class MacroScore {
    private final MacroScoreHolder holder;
    private final Objective objective;

    public MacroScore(MacroScoreHolder holder, Objective objective) {
        this.holder = holder;
        this.objective = objective;
    }

    public MacroScoreHolder getHolder() {
        return holder;
    }

    public Objective getObjective() {
        return objective;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        MacroScore that = (MacroScore) o;

        return holder.equals(that.holder) && objective.equals(that.objective);
    }

    @Override
    public int hashCode() {
        int result = holder.hashCode();
        result = 31 * result + objective.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return holder + " " + objective.getName();
    }
}
