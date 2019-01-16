package com.energyxxer.commodore.textcomponents;

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
    public String toString(TextStyle parentStyle) {
        if(children.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[");

        TextComponent listHeader = children.get(0);
        sb.append(listHeader.toString(parentStyle));

        for(int i = 1; i < children.size(); i++) {
            sb.append(',');
            sb.append(children.get(i).toString(parentStyle != null ? parentStyle.merge(listHeader.getStyle()) : listHeader.getStyle()));
        }

        sb.append(']');

        return sb.toString();
    }
}
