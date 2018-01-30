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
    private Entity entity;
    /**
     * The selector used to refer to the entity at the end of the command.
     * */
    private Selector selector;
    /**
     * The execute modifiers required to alter the execution context in order to effectively target the entity using the
     * selector.
     * <br>
     * This should be carefully constructed so that it doesn't clash with the execution context passed to the
     * Entity::getSelectorAs method.
     * */
    private ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

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

    public String toString() {
        return selector.toString();
    }
}
