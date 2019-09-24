package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class LocalScore {
    private final Objective objective;
    private final Entity holder;

    public LocalScore(@Nullable Entity holder, @Nullable Objective objective) {
        this.objective = objective;
        this.holder = holder;

        if(holder != null) holder.assertScoreHolderFriendly();
    }

    public LocalScore(@Nullable Objective objective, @Nullable Entity holder) {
        this.objective = objective;
        this.holder = holder;

        if(holder != null) holder.assertScoreHolderFriendly();
    }

    public Objective getObjective() {
        return objective;
    }

    public Entity getHolder() {
        return holder;
    }

    public String holderToString() {
        return holder != null ? holder.scoreHolderToString() : "*";
    }

    public String objectiveToString() {
        if(objective == null) return "*";
        if(!objective.getName().matches(VersionFeatureManager.getString("objectives.regex", CommandUtils.IDENTIFIER_ALLOWED))) {
            return CommandUtils.quote(objective.getName());
        } else {
            return objective.getName();
        }
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

    public void assertNotNull() {
        if(holder == null || objective == null) {
            throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Unexpected wildcard in score", this);
        }
    }

    public void assertHolderNotNull() {
        if(holder == null) {
            throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Unexpected wildcard holder", this);
        }
    }

    public void assertObjectiveNotNull() {
        if(objective == null) {
            throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Unexpected wildcard objective", this);
        }
    }
}
