package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ListTextComponent extends TextComponent {
    @NotNull
    private final ArrayList<@NotNull TextComponent> children = new ArrayList<>();

    public ListTextComponent() {
        this(Collections.emptyList());
    }

    public ListTextComponent(@NotNull TextComponent... children) {
        this(Arrays.asList(children));
    }

    public ListTextComponent(@NotNull Collection<@NotNull TextComponent> children) {
        children.forEach(this::append);
    }

    public void append(@NotNull TextComponent child) {
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
