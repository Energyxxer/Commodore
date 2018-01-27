package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Collection;

public class EntityResolution {
    private Selector selector;
    private ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public EntityResolution(Selector selector) {
        this.selector = selector;
    }

    public EntityResolution(Selector selector, Collection<ExecuteModifier> modifiers) {
        this.selector = selector;
        this.modifiers.addAll(modifiers);
    }
}
