package com.energyxxer.commodore.selector;

import com.energyxxer.commodore.types.Gamemode;

public class GamemodeArgument implements SelectorArgument {

    private Gamemode gamemode;
    private boolean negated;

    public GamemodeArgument(Gamemode gamemode) {
        this(gamemode, false);
    }

    public GamemodeArgument(Gamemode gamemode, boolean negated) {
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
}
