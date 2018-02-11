package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.inspection.EntityResolution;
import com.energyxxer.commodore.inspection.ExecutionContext;
import com.energyxxer.commodore.score.MacroScore;
import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.score.Objective;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class GenericEntity implements Entity {
    private final Selector selector;

    private Collection<ScoreboardAccess> scoreboardAccesses;

    private final ArrayList<MacroScoreHolder> macroHolders = new ArrayList<>();

    public GenericEntity(Selector selector) {
        this.selector = selector;
        addMacroHolder(new MacroScoreHolder("GE#" + this.hashCode()));
    }

    @Override
    public int getLimit() {
        return selector.getLimit();
    }

    @Override
    public Selector getSelector() {
        return selector;
    }

    @Override
    public EntityResolution resolveFor(ExecutionContext context) {
        if(context.getFinalSender() == this) return new EntityResolution(this, new Selector(Selector.BaseSelector.SENDER));
        return new EntityResolution(this, selector);
    }

    @Override
    public String toString() {
        return selector.toString();
    }

    @Override
    public GenericEntity clone() {
        GenericEntity copy = new GenericEntity(selector.clone());
        copy.addMacroHolders(macroHolders);
        return copy;
    }

    @Override
    public void addMacroHolder(MacroScoreHolder macro) {
        this.macroHolders.add(macro);
        scoreboardAccesses = null;
    }

    public void addMacroHolders(MacroScoreHolder... macros) {
        this.addMacroHolders(Arrays.asList(macros));
    }

    public void addMacroHolders(Collection<MacroScoreHolder> macros) {
        macros.forEach(this::addMacroHolder);
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(scoreboardAccesses == null) createScoreboardAccesses();
        return scoreboardAccesses;
    }

    private void createScoreboardAccesses() {
        ArrayList<MacroScore> scores = new ArrayList<>();
        for(MacroScoreHolder holder : getMacroHolders()) {
            for(Objective objective : selector.getObjectivesRead()) {
                scores.add(new MacroScore(holder, objective));
            }
        }
        if(scores.size() == 0)
            scoreboardAccesses = Collections.emptyList();
        else
            scoreboardAccesses = Collections.singletonList(new ScoreboardAccess(scores, ScoreboardAccess.AccessType.READ));
    }

    @Override
    public Collection<MacroScoreHolder> getMacroHolders() {
        return macroHolders;
    }

    @Override
    public boolean isPlayer() {
        return selector.isPlayer();
    }
}
