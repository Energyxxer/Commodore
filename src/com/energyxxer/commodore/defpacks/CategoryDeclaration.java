package com.energyxxer.commodore.defpacks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Represents a category declaration extracted from the pack.json of a definition pack.
 * */
public class CategoryDeclaration {
    /**
     * The category this declaration refers to.
     * */
    final @NotNull String category;

    /**
     * The true category this is an alias for.
     */
    @Nullable String aliasFor;
    /**
     * The directory name inside the <code><i>namespace/</i>tags/</code> directory in which the declaration specifies
     * that tags of this category should be imported and exported. May be <code>null</code> if not defined.
     * */
    String tagDirectory;
    /**
     * Whether types of this category should use the namespace prefix when printed onto a command.
     * */
    boolean useNamespace = true;

    private LinkedHashMap<String, DefinitionBlueprint> definitions;

    /**
     * Creates a category declaration object for the specified category.
     * */
    CategoryDeclaration(@NotNull String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoryDeclaration{" +
                "category='" + category + '\'' +
                ", aliasFor='" + aliasFor + '\'' +
                ", tagDirectory='" + tagDirectory + '\'' +
                ", useNamespace=" + useNamespace +
                '}';
    }

    public @NotNull String getCategory() {
        return category;
    }

    public @Nullable String getTagDirectory() {
        return tagDirectory;
    }

    public boolean usesNamespace() {
        return useNamespace;
    }

    public void putDefinition(DefinitionBlueprint definitionBlueprint) {
        if(aliasFor != null) throw new MalformedPackException("Cannot put definition for category '" + category + "': This is an alias category!");
        if(definitions == null) definitions = new LinkedHashMap<>();
        if(!definitions.containsKey(definitionBlueprint.name)) {
            definitions.put(definitionBlueprint.name, definitionBlueprint);
        } else {
            definitions.get(definitionBlueprint.name).join(definitionBlueprint);
        }
    }

    public Collection<DefinitionBlueprint> getBlueprints() {
        if(definitions == null) return Collections.emptyList();
        return definitions.values();
    }
}
