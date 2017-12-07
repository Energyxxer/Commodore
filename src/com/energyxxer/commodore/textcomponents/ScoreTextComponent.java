package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.score.LocalScore;

public class ScoreTextComponent extends TextComponent {
    private LocalScore score;

    public ScoreTextComponent(LocalScore score) {
        this.score = score;
    }

    public ScoreTextComponent(LocalScore score, TextStyle style) {
        this.score = score;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedName = "\"" + CommandUtils.escape(score.getParent().getHolder().getReference()) + "\"";
        String escapedObjective = "\"" + CommandUtils.escape(score.getObjective().getName()) + "\"";
        String baseProperties = this.getBaseProperties(parent);
        return "{\"score\":{\"name\":" +
                escapedName +
                ",\"objective\":" +
                escapedObjective +
                "}" +
                (baseProperties != null ? "," + baseProperties : "") +
                '}';
    }
}
