package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

public class KeybindTextComponent extends TextComponent {
    @NotNull
    private final String key;

    public KeybindTextComponent(@NotNull String key) {
        this.key = key;
    }

    public KeybindTextComponent(@NotNull String key, TextStyle style) {
        this.key = key;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String escapedText = "\"" + CommandUtils.escape(key) + "\"";
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"keybind\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.keybind");
        super.assertAvailable();
    }
}
