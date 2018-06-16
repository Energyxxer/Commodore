package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.tags.Tag;

import java.util.ArrayList;

public class TagBlueprint {
    final String namespace;
    final String name;
    Tag.OverridePolicy policy;
    ArrayList<String> content;
    boolean export;

    public TagBlueprint(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;

        content = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "#" + namespace + ":" + name + " (" + policy + "): " + content;
    }
}
