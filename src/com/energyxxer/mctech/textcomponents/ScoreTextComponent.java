package com.energyxxer.mctech.textcomponents;

import com.energyxxer.mctech.CommandUtils;
import com.energyxxer.mctech.score.ScoreHolder;

public class ScoreTextComponent extends TextComponent {
    private ScoreHolder name;
    private String objective;

    public ScoreTextComponent(ScoreHolder name, String objective) {
        this.name = name;
        this.objective = objective;
    }

    public ScoreTextComponent(ScoreHolder name, String objective, TextStyle style) {
        this.name = name;
        this.objective = objective;
        this.setStyle(style);
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedName = "\"" + CommandUtils.escape(name.getReference()) + "\"";
        String escapedObjective = "\"" + CommandUtils.escape(objective) + "\"";
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
