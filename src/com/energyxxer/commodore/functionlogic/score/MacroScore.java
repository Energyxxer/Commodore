package com.energyxxer.commodore.functionlogic.score;

import java.util.Objects;

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

    public boolean matches(MacroScore other) {
        return (other.holder == null || (this.holder != null && this.holder.equals(other.holder))) && (other.objective == null || this.objective == other.objective);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MacroScore that = (MacroScore) o;
        return Objects.equals(holder, that.holder) &&
                Objects.equals(objective, that.objective);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holder, objective);
    }

    @Override
    public String toString() {
        return ((holder != null) ? holder.toString() : "*") + " " + ((objective != null) ? objective.getName() : "*");
    }
}
