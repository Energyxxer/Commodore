package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.GamemodeType;

public class GamemodeArgument implements SelectorArgument {

    private GamemodeType gamemode;
    private boolean negated;

    public GamemodeArgument(GamemodeType gamemode) {
        this(gamemode, false);
    }

    public GamemodeArgument(GamemodeType gamemode, boolean negated) {
        this.gamemode = gamemode;
        this.negated = negated;
    }

    @Override
    public String getArgumentString() {
        return "gamemode=" + (negated ? "!" : "") + gamemode;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public GamemodeArgument clone() {
        return new GamemodeArgument(gamemode, negated);
    }

    @Override
    public String getKey() {
        return "gamemode";
    }

    @Override
    public ExecutionVariableMap getUsedExecutionVariables() {
        return null;
    }
}
