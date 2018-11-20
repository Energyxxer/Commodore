package com.energyxxer.commodore.functionlogic.inspection;

import com.energyxxer.commodore.functionlogic.commands.execute.ExecuteModifier;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.entity.EntityRepresentation;

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
    private final EntityRepresentation representation;

    /**
     * The execute modifiers required to alter the execution context in order to effectively target the entity using the
     * selector.
     * */
    private final ArrayList<ExecuteModifier> modifiers = new ArrayList<>();

    public EntityResolution(Entity entity, EntityRepresentation representation) {
        this(entity, representation, null);
    }

    public EntityResolution(Entity entity, EntityRepresentation representation, Collection<ExecuteModifier> modifiers) {
        this.entity = entity;
        this.representation = representation;
        if(modifiers != null) this.modifiers.addAll(modifiers);
    }

    public Entity getEntity() {
        return entity;
    }

    public ArrayList<ExecuteModifier> getModifiers() {
        return modifiers;
    }

    @Override
    public CommandEmbeddableResolution resolveEmbed(ExecutionContext execContext) {
        return new CommandEmbeddableResolution(representation.toString(), modifiers);
    }

    @Override
    public String toString() {
        return representation.toString();
    }
}
