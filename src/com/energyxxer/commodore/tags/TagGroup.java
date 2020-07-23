package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.module.Namespace;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class TagGroup<T extends Tag> {
    @NotNull
    private final Namespace namespace;
    @NotNull
    private final String category;
    @NotNull
    private final String directory;
    @NotNull
    private final ArrayList<T> tags = new ArrayList<>();

    @NotNull
    private final TagInstantiator<T> instantiator;

    public TagGroup(@NotNull Namespace namespace, @NotNull String category, @NotNull String directory, @NotNull TagInstantiator<T> instantiator) {
        this.namespace = namespace;
        this.category = category;
        this.directory = directory;
        this.instantiator = instantiator;
    }

    public T get(@NotNull String name) {
        for(T value : tags) {
            if(value.getName().equals(name)) return value;
        }
        return null;
    }

    @NotNull
    public T getOrCreate(@NotNull String name) {
        T existing = get(name);
        if(existing != null) return existing;

        T tag = instantiator.instantiate(this, this.namespace, name);

        this.tags.add(tag);

        return tag;
    }

    @NotNull
    public T create(@NotNull String name) {
        T existing = get(name);
        if(existing != null) throw new CommodoreException(CommodoreException.Source.DUPLICATION_ERROR, "'" + name + "' already exists as a '" + category + "' tag in the '" + namespace + "' namespace");

        T tag = instantiator.instantiate(this, this.namespace, name);

        this.tags.add(tag);

        return tag;
    }

    /**
     * Checks if a tag by the given name exists
     *
     * @param name The name to check if it exists.
     *
     * @return true if this group contains the given name, false otherwise
     * */
    public boolean exists(@NotNull String name) {
        for(T value : tags) {
            if(value.getName().equals(name)) return true;
        }
        return false;
    }

    @NotNull
    public Collection<T> getAll() {
        return new ArrayList<>(tags);
    }

    @NotNull
    public String getCategory() {
        return category;
    }

    public void join(@NotNull TagGroup other) {
        for(Object otherTag : other.tags) {
            T newTag = getOrCreate(((Tag) otherTag).getName());
            newTag.join((Tag) otherTag);
            newTag.setOverridePolicy(((Tag) otherTag).getOverridePolicy());
            newTag.setExport(((Tag) otherTag).shouldExport());
        }
    }

    @NotNull
    public String getDirectoryName() {
        return directory;
    }

    @Override
    public String toString() {
        return namespace + "#" + category + ": " + tags;
    }

    /**
     * Tells all the exportables currently in this function manager whether to export or not, by the given argument.
     *
     * @param shouldExport Whether all this function manager's exportables should export.
     * */
    public void propagateExport(boolean shouldExport) {
        for(T tag : this.tags) {
            tag.setExport(shouldExport);
        }
    }
}
