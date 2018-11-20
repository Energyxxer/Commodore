package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class GamemodeArgument implements SelectorArgument {

    private final Type gamemode;
    private final boolean negated;

    public GamemodeArgument(Type gamemode) {
        this(gamemode, false);
    }

    public GamemodeArgument(Type gamemode, boolean negated) {
        this.gamemode = gamemode;
        this.negated = negated;

        assertGamemode(gamemode);
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
