package com.energyxxer.commodore.score;

import java.util.Collection;
import java.util.Collections;

public class FakePlayer implements ScoreHolder {
    private final String name;

    private final MacroScoreHolder macroHolder;

    public FakePlayer(String name) {
        this.name = name;
        this.macroHolder = new MacroScoreHolder("FakePlayer#" + name);
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
