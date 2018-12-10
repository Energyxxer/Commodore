package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;

public class SelectorTextComponent extends TextComponent {
    @NotNull
    private final Entity entity;

    public SelectorTextComponent(@NotNull Entity entity) {
        this(entity, null);
    }

    public SelectorTextComponent(@NotNull Entity entity, @Nullable  TextStyle style) {
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
        return "{\"selector\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(entity);
    }
}
