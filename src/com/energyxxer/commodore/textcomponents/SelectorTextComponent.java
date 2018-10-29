package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;

import java.util.Collection;
import java.util.Collections;

public class SelectorTextComponent extends TextComponent {

    private final Entity entity;

    public SelectorTextComponent(Entity entity) {
        this(entity, null);
    }

    public SelectorTextComponent(Entity entity, TextStyle style) {
        this.entity = entity;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedText = "\"\be#\r\"";
        String baseProperties = this.getBaseProperties(parent);
        return (baseProperties == null) ? escapedText :
                "{\"selector\":" +
                        escapedText +
                        "," +
                        baseProperties +
                        '}';
    }

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(entity);
    }
}
