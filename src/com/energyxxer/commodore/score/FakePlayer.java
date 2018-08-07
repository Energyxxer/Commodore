package com.energyxxer.commodore.score;

import com.energyxxer.commodore.inspection.CommandEmbeddableResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;

import java.util.Collection;
import java.util.Collections;

/**
 * Implements a score holder that represents a player referred by name, which may or may not exist as a player entity.
 * */
public class FakePlayer implements ScoreHolder {
    /**
     * The name of this fake player.
     * */
    private final String name;

    /**
     * The {@link MacroScoreHolder}s that describe this fake player.
     * */
    private final MacroScoreHolder macroHolder;

    /**
     * Creates a fake player with the given name.
     *
     * @param name The name of the new fake player.
     * */
    public FakePlayer(String name) {
        this.name = name;
        this.macroHolder = new MacroScoreHolder("FakePlayer#" + name);
    }

    @Override
    public CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return new CommandEmbeddableResolution(name);
    }

    @Override
    public Collection<MacroScoreHolder> getMacroHolders() {
        return Collections.singletonList(macroHolder);
    }

    @Override
    public String toString() {
        return name;
    }
}
