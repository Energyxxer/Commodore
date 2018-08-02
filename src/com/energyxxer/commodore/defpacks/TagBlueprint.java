package com.energyxxer.commodore.defpacks;

import com.energyxxer.commodore.tags.Tag;

import java.util.ArrayList;

/**
 * Represents a tag, as obtained from a definition pack's data.
 * */
class TagBlueprint {
    /**
     * The namespace this tag belongs to.
     * */
    final String namespace;
    /**
     * The name or path to this tag.
     * */
    final String name;
    /**
     * How this tag should behave when placed on top of another data pack with the same tag.
     * */
    Tag.OverridePolicy policy;
    /**
     * The raw contents of this tag, as retrieved from its .json file.
     * */
    ArrayList<String> content;
    /**
     * Whether the generated tag object should export into the compiled data pack. By default this is
     * <code>false</code>, but it can be overridden by the definition pack's tag .json files.
     * */
    boolean export;

    /**
     * Creates a tag blueprint for a tag of the given namespace and name.
     *
     * @param namespace The namespace this tag belongs to.
     * @param name The name of this tag.
     * */
    TagBlueprint(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;

        content = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "#" + namespace + ":" + name + " (" + policy + "): " + content;
    }
}
