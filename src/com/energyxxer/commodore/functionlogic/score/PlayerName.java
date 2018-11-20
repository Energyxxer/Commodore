package com.energyxxer.commodore.functionlogic.score;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.entity.EntityRepresentation;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddableResolution;
import com.energyxxer.commodore.functionlogic.inspection.EntityResolution;
import com.energyxxer.commodore.functionlogic.inspection.ExecutionContext;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Implements a score holder that represents a player referred by name, which may or may not exist as a player entity.
 * */
public class PlayerName implements Entity, EntityRepresentation {
    /**
     * The name of this fake player.
     * */
    private final String name;

    /**
     * The {@link MacroScoreHolder}s for this entity.
     * */
    private final ArrayList<MacroScoreHolder> macroHolders = new ArrayList<>();

    /**
     * Creates a fake player with the given name.
     *
     * @param name The name of the new fake player.
     * */
    public PlayerName(String name) {
        this.name = name;
        this.macroHolders.add(new MacroScoreHolder("PlayerName#" + name));
    }

    @Override
    public CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return new CommandEmbeddableResolution(name);
    }

    @Override
    public Collection<MacroScoreHolder> getMacroHolders() {
        return macroHolders;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public EntityResolution resolveFor(ExecutionContext context) {
        return new EntityResolution(this, this);
    }

    @Override
    public void addMacroHolder(MacroScoreHolder macro) {
        this.macroHolders.add(macro);
    }

    @Override
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    @Override
    public int getLimit() {
        return 1;
    }

    @Override
    public Entity limitToOne() {
        return this;
    }

    @Override
    public Entity clone() {
        PlayerName copy = new PlayerName(this.name);
        copy.macroHolders.addAll(this.macroHolders);
        return copy;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
