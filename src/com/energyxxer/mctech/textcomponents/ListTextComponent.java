package com.energyxxer.mctech.textcomponents;

import java.util.ArrayList;

public class ListTextComponent extends TextComponent {

    private ArrayList<TextComponent> children = new ArrayList<>();

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
