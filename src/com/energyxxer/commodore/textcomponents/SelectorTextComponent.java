package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public String toString(TextStyle parentStyle) {
        String escapedText = "\"" + CommandUtils.escape(entity.toString()) + "\"";
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"selector\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }

}
