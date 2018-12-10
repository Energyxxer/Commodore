package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringTextComponent extends TextComponent {
    @NotNull
    private final String text;

    public StringTextComponent(@NotNull String text) {
        this.text = text;
    }

    public StringTextComponent(@NotNull String text, @Nullable TextStyle style) {
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
