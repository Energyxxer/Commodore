package com.energyxxer.commodore.score;

import java.util.Collection;
import java.util.Collections;

public class FakePlayer implements ScoreHolder {
    private String name;

    private MacroScoreHolder macroHolder;

    public FakePlayer(String name) {
        this.name = name;
        this.macroHolder = new MacroScoreHolder("FakePlayer#" + name);
    }

    @Override
    public String getReference() {
        return name;
    }

    @Override
    public Collection<MacroScoreHolder> getMacroHolders() {
        return Collections.singletonList(macroHolder);
    }
}
