package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CommandEmbeddableResolution {
    private final ArrayList<ExecuteModifier> newModifiers = new ArrayList<>();
    private final String embedString;

    public CommandEmbeddableResolution(String embedString, ExecuteModifier... newModifiers) {
        this(embedString, Arrays.asList(newModifiers));
    }

    public CommandEmbeddableResolution(String embedString, Collection<ExecuteModifier> newModifiers) {
        this.embedString = embedString;
        this.newModifiers.addAll(newModifiers);
    }

    public Collection<ExecuteModifier> getNewModifiers() {
        return newModifiers;
    }

    public String getEmbedString() {
        return embedString;
    }
}
