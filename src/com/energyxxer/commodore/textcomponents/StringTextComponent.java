package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;

public class StringTextComponent extends TextComponent {
    private String text;

    public StringTextComponent(String text) {
        this.text = text;
    }

    public StringTextComponent(String text, TextStyle style) {
        this.text = text;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedText = "\"" + CommandUtils.escape(text) + "\"";
        String baseProperties = this.getBaseProperties(parent);
        return (baseProperties == null) ? escapedText :
                "{\"text\":" +
                escapedText +
                "," +
                baseProperties +
                '}';
    }
}
