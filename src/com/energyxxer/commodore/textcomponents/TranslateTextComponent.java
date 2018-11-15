package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;

public class TranslateTextComponent extends TextComponent {
    private final String translateKey;

    public TranslateTextComponent(String translateKey) {
        this.translateKey = translateKey;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedText = "\"" + CommandUtils.escape(translateKey) + "\"";
        String baseProperties = this.getBaseProperties(parent);
        return "{\"translate\":" +
                        escapedText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }
}
