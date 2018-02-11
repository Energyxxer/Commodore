package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.inspection.CommandEmbeddable;
import com.energyxxer.commodore.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ListTextComponent extends TextComponent {

    private final ArrayList<TextComponent> children = new ArrayList<>();

    public ListTextComponent() {
    }

    public void append(TextComponent child) {
        children.add(child);
    }

    @Override
    public boolean supportsProperties() {
        return false;
    }

    @Override
    public @NotNull Collection<ScoreboardAccess> getScoreboardAccesses() {
        ArrayList<ScoreboardAccess> accesses = new ArrayList<>();
        children.forEach(c -> accesses.addAll(c.getScoreboardAccesses()));
        return accesses;
    }

    @Override
    public Collection<CommandEmbeddable> getEmbeddables() {
        ArrayList<CommandEmbeddable> embeddables = new ArrayList<>();
        children.forEach(c -> embeddables.addAll(c.getEmbeddables()));
        return embeddables;
    }

    @Override
    public String toString(TextComponent parent) {
        if(children.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        TextComponent listHeader = children.get(0);
        sb.append(listHeader.toString(parent));

        for(int i = 1; i < children.size(); i++) {
            sb.append(',');
            sb.append(children.get(i).toString(listHeader));
        }

        sb.append(']');

        return sb.toString();
    }
}
