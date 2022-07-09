package com.energyxxer.commodore.types;

import com.energyxxer.commodore.CommandUtils;
import com.energyxxer.commodore.defpacks.DefinitionPack;
import com.energyxxer.commodore.module.CommandModule;
import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.GenericType;
import com.energyxxer.commodore.types.defaults.TypeManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

/**
 * Defines a type of a Minecraft object of a certain category.<br>
 *     Such categories are, by default, blocks, items, fluids, entities, biomes, difficulties, dimensions,
 *     status effects, enchantments, gamemodes, gamerules, item slots, particles, structures, functions and bossbars.
 *     <br>
 *
 * These types can be provided either via definition packs or by creating them via the {@link TypeDictionary#create}
 * method of the corresponding category in the {@link TypeManager} of the corresponding {@link Namespace} of the
 * {@link CommandModule}.
 *
 * Definition packs can also define their own categories.
 *
 * @see DefinitionPack
 *
 * @see TypeDictionary
 * @see TypeManager
 * @see Namespace
 * @see CommandModule
 *
 * @see GenericType
 * */
public abstract class Type {
    /**
     * The case-sensitive string describing the category of this type.
     * */
    @NotNull
    protected final String category;
    /**
     * The namespace this type belongs to.
     * */
    protected final Namespace namespace;
    /**
     * Whether this type uses its namespace when specified
     * */
    protected boolean usesNamespace = true;
    /**
     * The name this type is referred to as.
     * */
    @NotNull
    protected final String name;
    /**
     * The properties of this type, as defined via the JSON declaration of this type in a definition pack, or given
     * directly by the {@link Type#putProperty(String, String)} and {@link Type#putProperties(HashMap)} methods.
     * */
    @NotNull
    protected final HashMap<String, String> properties = new HashMap<>();

    /**
     * Creates a type with the specified fields.
     *
     * @param category The case-sensitive string describing the category of this type.
     * @param namespace The Namespace this type belongs to. Can be null if {@link Type#useNamespace} returns false.
     * @param name The name this type is referred to as.
     * */
    public Type(@NotNull String category, Namespace namespace, @NotNull String name) {
        this.category = category;
        this.namespace = namespace;
        this.name = name;
    }

    /**
     * Describes whether or not this type is namespace-sensitive, and whether it should print the namespace when used in
     * a command. If this returns true, the {@link Type#namespace} field should not be null.
     *
     * @return <code>true</code> if the namespace is a sensitive part of its declaration, <code>false</code> if it's
     * not.
     * */
    public boolean useNamespace() {
        return usesNamespace;
    }

    /**
     * Describes whether or not this type alone is enough to set an instance of itself. For instance, if this
     * type object refers to a block <i>tag</i>, this type is not standalone, as it is not enough to be able to set
     * a block of this type. In short, this dictates whether or not this type is a standalone definition, or if it
     * describes multiple types at once.
     *
     * @return <code>true</code> if this type can be set and tested, <code>false</code> if this type can only be tested.
     * */
    public abstract boolean isStandalone();

    /**
     * Returns the name this type is referred to as.
     *
     * @return The name of this type.
     * */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the namespace this type belongs to.
     *
     * @return The namespace of this type.
     * */
    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * Returns the category this type belongs to.
     *
     * @return The category of this type.
     * */
    @NotNull
    public String getCategory() {
        return category;
    }

    /**
     * Checks whether this type is of the given category name,
     * respecting the category aliases of this type's namespace.
     */
    public boolean isCategory(String category) {
        if(this.category.equals(category)) return true;
        return namespace.resolveAlias(this.category).equals(namespace.resolveAlias(category));
    }

    /**
     * Adds a property to this type.
     *
     * @param key The key with which the specified property is to be associated.
     * @param value The value to be associated with the specified key.
     * */
    public void putProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Adds several properties to this type.
     *
     * @param properties A string-string map containing all the properties to add to this type.
     * */
    public void putProperties(HashMap<String, String> properties) {
        this.properties.putAll(properties);
    }

    /**
     * If this type has a property with the given key, the value associated with that key is returned.
     * If it doesn't contain that key, <code>null is returned</code>
     *
     * @param key The key whose associated value is to be returned.
     *
     * @return The value associated with the given key.
     * */
    public String getProperty(String key) {
        return properties.get(key);
    }

    /**
     * Retrieves the map containing this type's properties.
     *
     * @return The map of this type's properties. Any modifications to the returned
     * value will also be made to this type's properties.
     * */
    @NotNull
    public HashMap<String, String> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return (isStandalone() ? "" : "#") + ((useNamespace()) ? (getNamespace().getName() + ":") : "") + getName();
    }

    public String toSafeString() {
        return CommandUtils.quoteIfNecessary(toString(), "types." + category + ".regex", "types.regex", ".+");
    }

    public String toStringExcludeMinecraftNamespace() {
        return (isStandalone() ? "" : "#") + ((useNamespace() && !getNamespace().getName().equals("minecraft")) ? (getNamespace().getName() + ":") : "") + getName();
    }

    public String toSafeStringExcludeMinecraftNamespace() {
        return CommandUtils.quoteIfNecessary(toStringExcludeMinecraftNamespace(), "types." + category + ".regex", "types.regex", ".+");
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Type otherType = (Type) o;

        return (!useNamespace() || getNamespace().equals(otherType.getNamespace())) && getName().equals(otherType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getNamespace(), getName(), getProperties());
    }
}
