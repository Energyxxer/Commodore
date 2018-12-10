package com.energyxxer.commodore.functionlogic.score;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MacroScore {
    @Nullable
    private final MacroScoreHolder holder;
    @Nullable
    private final Objective objective;

    public MacroScore(@Nullable MacroScoreHolder holder, @Nullable Objective objective) {
        this.holder = holder;
        this.objective = objective;
    }

    @Nullable
    public MacroScoreHolder getHolder() {
        return holder;
    }

    @Nullable
    public Objective getObjective() {
        return objective;
    }

    public boolean matches(@NotNull MacroScore other) {
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
