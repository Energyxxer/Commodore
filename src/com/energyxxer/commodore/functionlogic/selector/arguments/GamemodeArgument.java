package com.energyxxer.commodore.functionlogic.selector.arguments;

import com.energyxxer.commodore.functionlogic.inspection.ExecutionVariableMap;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertGamemode;

public class GamemodeArgument implements SelectorArgument {

    @NotNull
    public final Type gamemode;
    public final boolean negated;

    public GamemodeArgument(@NotNull Type gamemode) {
        this(gamemode, false);
    }

    public GamemodeArgument(@NotNull Type gamemode, boolean negated) {
        this.gamemode = gamemode;
        this.negated = negated;

        assertGamemode(gamemode);
    }

    @NotNull
    @Override
    public String getArgumentString() {
        return "gamemode=" + (negated ? "!" : "") + gamemode.toSafeString();
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @NotNull
    @Override
    public GamemodeArgument clone() {
        return new GamemodeArgument(gamemode, negated);
    }

    @NotNull
    @Override
    public String getKey() {
        return "gamemode";
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
        GamemodeArgument that = (GamemodeArgument) o;
        return negated == that.negated &&
                gamemode.equals(that.gamemode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamemode, negated);
    }
}
