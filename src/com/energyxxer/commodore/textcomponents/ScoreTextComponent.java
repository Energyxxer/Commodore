package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.score.LocalScore;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public class ScoreTextComponent extends TextComponent {
    private final LocalScore score;
    private final Collection<ScoreboardAccess> accesses;

    public ScoreTextComponent(LocalScore score) {
        this(score, null);
    }

    public ScoreTextComponent(LocalScore score, TextStyle style) {
        this.score = score;
        this.setStyle(style);

        this.accesses = Collections.singletonList(new ScoreboardAccess(score.getMacroScores(), ScoreboardAccess.AccessType.READ));
    }

    @Override
    public boolean supportsProperties() {
        return true;
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        return accesses;
    }

    @Override
    public String toString(TextComponent parent) {
        String escapedName = "\"\be#\r\"";
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

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.singletonList(score.getHolder());
    }
}
