package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.defaults.TeamReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeamArgument implements SelectorArgument {
    @Nullable
    private final TeamReference team;
    private final boolean negated;

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
}
