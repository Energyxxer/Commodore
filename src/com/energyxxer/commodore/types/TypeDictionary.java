package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;
import com.energyxxer.commodore.types.defaults.TypeManager;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class which contains all the types of a specific category in a certain namespace.<br>
 *
 * @param <T> Refers to the {@link Type} subclass which will be instantiated when a type is created
 *     in this dictionary and, by extension, the class of the object returned by methods {@link TypeDictionary#create}
 *     and {@link TypeDictionary#get}.
 *
 * @see Type
 * @see TypeManager
 * @see Namespace
 * */
public class TypeDictionary<T extends Type> {
    /**
     * The namespace this type dictionary belongs to.
     * */
    @NotNull
    private final Namespace namespace;
    /**
     * The case-sensitive string which describes which category all the containing types belong to.
     * */
    @NotNull
    private final String category;

    /**
     * A map containing all the types in this dictionary, where the key is the name of the type (without the namespace),
     * and the value is the type object.
     *
     * The type values are of the class bound by <code>T</code>.
     * */
    @NotNull
    private final HashMap<@NotNull String, @NotNull T> types = new HashMap<>();
    /**
     * The functional interface for creating type objects for the type bound by <code>T</code>
     * */
    @NotNull
    private final TypeInstantiator<T> instantiator;

    /**
     * Creates a type dictionary belonging to the given namespace and category, and which creates a type via the given
     * instantiator.
     *
     * @param namespace The namespace all types created in this dictionary will belong to.
     * @param category The category all types created in this dictionary will belong to.
     * @param instantiator The instantiator which will create all types under this dictionary.
     * */
    public TypeDictionary(@NotNull Namespace namespace, @NotNull String category, @NotNull TypeInstantiator<T> instantiator) {
        this.namespace = namespace;
        this.category = category;
        this.instantiator = instantiator;
    }

    /**
     * Creates a type with the given name. If a type by that name already exists in this dictionary, that
     * already-existing type will be returned. Otherwise it will be created.
     *
     * @param name The name the new type will be referred to as.
     *
     * @return The newly-created or already-existing type by the given name, of the type bound by <code>T</code>.
     * */
    @NotNull
    public T create(@NotNull String name) {
        T existing = types.get(name);
        if(existing != null) return existing;

        T newType = instantiator.create(name);
        types.put(name, newType);
        return newType;
    }

    /**
     * Retrieves a type by the given name.
     *
     * @param name The name for which to find a type.
     *
     * @return The type by the given name, if it exists.
     *
     * @throws TypeNotFoundException if there is not a type in this dictionary by the given name.
     * */
    public T get(@NotNull String name) throws TypeNotFoundException {
        T existing = types.get(name);
        if(existing != null) return existing;
        throw new TypeNotFoundException("'" + name + "' does not exist as '" + category + "' in the '" + namespace + "' namespace");
    }

    /**
     * Retrieves a list of all currently present types under this type dictionary.
     *
     * @return A collection with the types of this dictionary.
     * */
    @NotNull
    public Collection<T> list() {
        return types.values();
    }

    /**
     * Adds all of the given dictionary's types into this dictionary.
     *
     * @param other The dictionary of which all types will be added to this dictionary.
     * */
    public void join(@NotNull TypeDictionary<T> other) {
        for(Map.Entry<String, T> entry : other.types.entrySet()) {
            this.types.putIfAbsent(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Retrieves the category that this type dictionary creates types of.
     *
     * @return The string describing the category of all types inside this dictionary.
     * */
    @NotNull
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "TypeDictionary{" +
                "namespace=" + namespace +
                ", category='" + category + '\'' +
                ", types=" + types.values() +
                '}';
    }
}
