package com.energyxxer.commodore.functionlogic.entity;

import com.energyxxer.commodore.functionlogic.inspection.EntityResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.MacroScore;
import com.energyxxer.commodore.functionlogic.score.MacroScoreHolder;
import com.energyxxer.commodore.functionlogic.score.Objective;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import com.energyxxer.commodore.functionlogic.selector.Selector;
import com.energyxxer.commodore.functionlogic.selector.arguments.LimitArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Represents a simple entity from a selector.
 * */
public class GenericEntity implements Entity {
    /**
     * The selector this generic entity should utilize.
     * */
    private final Selector selector;

    /**
     * Lazily-instantiated scoreboard accesses for this generic entity, based on the selector's objectives and this
     * entity's macro score holders.
     * */
    private Collection<ScoreboardAccess> scoreboardAccesses;

    /**
     * The {@link MacroScoreHolder}s for this entity.
     * */
    private final ArrayList<MacroScoreHolder> macroHolders = new ArrayList<>();

    /**
     * Creates a generic entity with the given selector.
     *
     * @param selector The selector this generic entity should resolve to.
     * */
    public GenericEntity(Selector selector) {
        this.selector = selector;
        addMacroHolder(new MacroScoreHolder("GE#" + this.hashCode()));
    }

    /**
     * Retrieves this generic entity's selector object. Any changes to the return value will be reflected in this
     * generic entity.
     *
     * @return The selector for this generic entity.
     * */
    public Selector getSelector() {
        return selector;
    }

    /**
     * Adds the given {@link MacroScoreHolder}s to this generic entity's list.
     *
     * @param macros The MacroScoreHolders to mark this entity with.
     * */
    public void addMacroHolders(MacroScoreHolder... macros) {
        this.addMacroHolders(Arrays.asList(macros));
    }

    /**
     * Adds the given {@link MacroScoreHolder}s to this generic entity's list.
     *
     * @param macros The MacroScoreHolders to mark this entity with.
     * */
    public void addMacroHolders(Collection<MacroScoreHolder> macros) {
        macros.forEach(this::addMacroHolder);
    }

    /**
     * Lazily instantiates this generic entity's scoreboard accesses, using the selector's used objectives and this
     * generic entity's MacroScoreHolders.
     * */
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
    public int getLimit() {
        return selector.getLimit();
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

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        if(scoreboardAccesses == null) createScoreboardAccesses();
        return scoreboardAccesses;
    }

    @Override
    public Collection<MacroScoreHolder> getMacroHolders() {
        return macroHolders;
    }

    @Override
    public boolean isPlayer() {
        return selector.isPlayer() || selector.getBase().equals(Selector.BaseSelector.SENDER);
    }

    @Override
    public GenericEntity limitToOne() {
        Selector newSelector = selector.clone();
        newSelector.addArgument(new LimitArgument(1));
        GenericEntity clone = new GenericEntity(newSelector);
        clone.addMacroHolders(macroHolders);
        return clone;
    }
}
