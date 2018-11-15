package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;

public class KeybindTextComponent extends TextComponent {
    private final String key;

    public KeybindTextComponent(String key) {
        this.key = key;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedText = "\"" + CommandUtils.escape(key) + "\"";
        String baseProperties = this.getBaseProperties(parent);
        return "{\"keybind\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }
}
