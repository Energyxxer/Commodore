package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.TeamReference;

public class TeamArgument implements SelectorArgument {

    private final TeamReference team;
    private final boolean negated;

    public TeamArgument(TeamReference team) {
        this(team, false);
    }

    public TeamArgument(TeamReference team, boolean negated) {
        this.team = team;
        this.negated = negated;
    }

    @Override
    public String getArgumentString() {
        return "team=" + (negated ? "!" : "") + team;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public TeamArgument clone() {
        return new TeamArgument(team, negated);
    }

    @Override
    public String getKey() {
        return "team";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }

    public static SelectorArgumentParseResult parse(String str) {
        throw new UnsupportedOperationException("Team argument is unsupported for parsing operations");
    }
}
