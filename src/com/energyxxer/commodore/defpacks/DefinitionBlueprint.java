package com.energyxxer.commodore.defpacks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Represents an entry in a type definition file that describes information about a specific type.
 * */
public class DefinitionBlueprint {
    /**
     * The namespace this type belongs to. May be null if there is no namespace associated with it.
     * */
    @Nullable
    final String namespace;
    /**
     * The name of this type.
     * */
    @NotNull
    final String name;
    /**
     * A map containing this type's defined properties.
     * */
    @NotNull
    final HashMap<String, String> properties;

    /**
     * Creates a definition blueprint with the specified name, namespace and property map.
     *
     * @param name The full name of this type (including the namespace prefix, if applicable and the useNamespace param
     *             reflects that).
     * @param properties The map of this type's properties.
     * @param useNamespace Whether this type should be namespace-sensitive.
     * */
    DefinitionBlueprint(@NotNull String name, @NotNull HashMap<String, String> properties, boolean useNamespace) {
        if(useNamespace) {
            if(name.contains(":")) {
                this.namespace = name.substring(0, name.indexOf(":"));
                this.name = name.substring(name.indexOf(":")+1);
            } else {
                this.namespace = "minecraft";
                this.name = name;
            }
        } else {
            this.namespace = null;
            this.name = name;
        }
        this.properties = properties;
    }

    /**
     * Retrieves this blueprint's namespace.
     *
     * @return The namespace of this blueprint.
     * */
    public @Nullable String getNamespace() {
        return namespace;
    }

    /**
     * Retrieves this blueprint's name.
     *
     * @return The name of this blueprint.
     * */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Retrieves this blueprint's properties.
     *
     * @return The properties of this blueprint.
     * */
    public @NotNull HashMap<String, String> getProperties() {
        return new HashMap<>(properties);
    }

    @Override
    public String toString() {
        return "DefinitionBlueprint{" +
                "name='" + namespace + ':' + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
