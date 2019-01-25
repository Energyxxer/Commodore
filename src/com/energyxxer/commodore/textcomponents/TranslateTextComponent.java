package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import org.jetbrains.annotations.NotNull;

public class TranslateTextComponent extends TextComponent {
    @NotNull
    private final String translateKey;
    private ListTextComponent with = null;

    public TranslateTextComponent(@NotNull String translateKey) {
        this.translateKey = translateKey;
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    public void addWith(TextComponent component) {
        if(this.with == null) this.with = new ListTextComponent();
        this.with.append(component);
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String escapedText = "\"" + CommandUtils.escape(translateKey) + "\"";
        String withText = (with != null) ? ",\"with\":" + with.toString(this.style.merge(parentStyle)) : "";
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"translate\":" +
                        escapedText +
                        withText +
                        (baseProperties != null ? "," + baseProperties : "") +
                        '}';
    }
}
