package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TeamArgument implements SelectorArgument {
    @Nullable
    public final TeamReference team;
    public final boolean negated;

    public TeamArgument(@Nullable TeamReference team) {
        this(team, false);
    }

    public TeamArgument(@Nullable TeamReference team, boolean negated) {
        this.team = team;
        this.negated = negated;
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "team=" + (negated ? "!" : "") + (team != null ? team.toSafeString() : "");
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @NotNull
    @Override
    public TeamArgument clone() {
        return new TeamArgument(team, negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "team";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    @Override
    public String toString() {
        return getArgumentString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamArgument that = (TeamArgument) o;
        return negated == that.negated &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, negated);
    }
}
