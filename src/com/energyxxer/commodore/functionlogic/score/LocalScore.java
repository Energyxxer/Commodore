package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LocalScore {
    @Nullable
    private final Objective objective;
    @Nullable
    private final Entity holder;

    public LocalScore(@Nullable Entity holder, @Nullable Objective objective) {
        this.objective = objective;
        this.holder = holder;
    }

    public LocalScore(@Nullable Objective objective, @Nullable Entity holder) {
        this.objective = objective;
        this.holder = holder;
    }

    @Nullable
    public Objective getObjective() {
        return objective;
    }

    @Nullable
    public Entity getHolder() {
        return holder;
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
