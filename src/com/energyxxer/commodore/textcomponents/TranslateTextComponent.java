package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TranslateTextComponent extends TextComponent {
    @NotNull
    private final String translateKey;
    private ArrayList<TextComponent> with = null;

    public TranslateTextComponent(@NotNull String translateKey) {
        this.translateKey = translateKey;
    }

    public TranslateTextComponent(@NotNull String translateKey, TextStyle style) {
        this.translateKey = translateKey;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    public void addWith(TextComponent component) {
        if(this.with == null) this.with = new ArrayList<>();
        this.with.add(component);
    }

    private String constructWith() {
        if(with.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        sb.append(with.get(0).toString());

        for(int i = 1; i < with.size(); i++) {
            sb.append(',');
            sb.append(with.get(i).toString());
        }

        sb.append(']');

        return sb.toString();
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String escapedText = "\"" + CommandUtils.escape(translateKey) + "\"";
        String withText = (with != null) ? ",\"with\":" + constructWith() : "";
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"translate\":" +
                escapedText +
                withText +
                (baseProperties != null ? "," + baseProperties : "") +
                '}';
    }

    @Override
    public void assertAvailable() {
        VersionFeatureManager.assertEnabled("textcomponent.translate");
        super.assertAvailable();
    }
}
