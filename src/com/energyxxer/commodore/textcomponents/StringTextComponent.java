package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
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
    public String toString(TextStyle parentStyle) {
        String escapedText = "\"" + CommandUtils.escape(text) + "\"";
        String baseProperties = this.getBaseProperties(parentStyle);
        return (baseProperties == null) ? escapedText :
                "{\"text\":" +
                        escapedText +
                        "," +
                        baseProperties +
                        '}';
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.text");
        super.assertAvailable();
    }
}
