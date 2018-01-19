package com.energyxxer.commodore.entity;

import com.energyxxer.commodore.score.MacroScoreHolder;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GenericEntity implements Entity {
    private Selector selector;

    private ArrayList<MacroScoreHolder> macroHolders = new ArrayList<>();

    public GenericEntity(Selector selector) {
        this.selector = selector;
        macroHolders.add(new MacroScoreHolder("GenericEntity#" + this.hashCode() + ":" + selector));
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
    public String toString() {
        return selector.toString();
    }

    @Override
    public GenericEntity clone() {
        return new GenericEntity(selector.clone());
    }

    public void addMacroHolder(MacroScoreHolder macro) {
        this.macroHolders.add(macro);
    }

    public void addMacroHolders(MacroScoreHolder... macros) {
        this.addMacroHolders(Arrays.asList(macros));
    }

    public void addMacroHolders(Collection<MacroScoreHolder> macros) {
        macros.forEach(this::addMacroHolder);
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
