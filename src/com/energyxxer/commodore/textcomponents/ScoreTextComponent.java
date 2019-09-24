package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import org.jetbrains.annotations.NotNull;

public class ScoreTextComponent extends TextComponent {
    @NotNull
    private final LocalScore score;

    public ScoreTextComponent(LocalScore score) {
        this(score, null);
    }

    public ScoreTextComponent(@NotNull LocalScore score, TextStyle style) {
        this.score = score;
        this.setStyle(style);

        score.assertObjectiveNotNull();
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public String toString(TextStyle parentStyle) {
        String escapedName = score.getHolder() == null ? "\"*\"" : "\"" + CommandUtils.escape(score.getHolder().toString()) + "\"";
        String escapedObjective = "\"" + CommandUtils.escape(score.getObjective().getName()) + "\"";
        String baseProperties = this.getBaseProperties(parentStyle);
        return "{\"score\":{\"name\":" +
                escapedName +
                ",\"objective\":" +
                escapedObjective +
                "}" +
                (baseProperties != null ? "," + baseProperties : "") +
                '}';
    }

}
