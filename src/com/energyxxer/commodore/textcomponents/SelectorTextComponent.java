package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectorTextComponent extends TextComponent {
    @NotNull
    private final Entity entity;
    @Nullable
    private final TextComponent separator;

    public SelectorTextComponent(@NotNull Entity entity) {
        this(entity, null);
    }

    public SelectorTextComponent(@NotNull Entity entity, @Nullable TextStyle style) {
        this(entity, style, null);
    }

    public SelectorTextComponent(@NotNull Entity entity, @Nullable TextStyle style, @Nullable TextComponent separator) {
        this.entity = entity;
        this.setStyle(style);
        entity.assertEntityFriendly();
        this.separator = separator;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String entityString = CommandUtils.quote(entity.selectorTextComponentToString());
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"selector\":" +
                        entityString +
                        (separator != null ? ",\"separator\":" + separator.toString(style) : "") +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.selector");
        super.assertAvailable();
        entity.assertAvailable();
        if(separator != null) VersionFeatureManager.assertEnabled("textcomponent.separators");
    }
}
