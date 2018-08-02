package com.energyxxer.commodore.inspection;

import com.energyxxer.commodore.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Collection;

public class EntityResolution implements CommandEmbeddable {

    /**
     * The entity or entities this object attempts to resolve.
     * */
    private final Entity entity;

    /**
     * The selector used to refer to the entity at the end of the command.
     * */
    private final Selector selector;

    /**
     * The execute modifiers required to alter the execution context in order to effectively target the entity using the
     * selector.
     * */
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public EntityResolution(Entity entity, Selector selector) {
        this(entity, selector, null);
    }

    public EntityResolution(Entity entity, Selector selector, Collection<ExecuteModifier> modifiers) {
        this.entity = entity;
        this.selector = selector;
        if(modifiers != null) this.modifiers.addAll(modifiers);
    }

    public Entity getEntity() {
        return entity;
    }

    public Selector getSelector() {
        return selector;
    }

    public ArrayList<ExecuteModifier> getModifiers() {
        return modifiers;
    }

    @Override
    public CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return new CommandEmbeddableResolution(selector.toString(), modifiers);
    }

    @Override
    public String toString() {
        return selector.toString();
    }
}
