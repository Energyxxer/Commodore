package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.functionlogic.functions.Function;
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

    @NotNull
    public T create(@NotNull String name) {
        T existing = get(name);
        if(existing != null) return existing;

        T tag = instantiator.instantiate(this, this.namespace, name);

        this.tags.add(tag);

        return tag;
    }

    public T get(@NotNull String name) {
        for(T value : tags) {
            if(value.getName().equals(name)) return value;
        }
        return null;
    }

    @NotNull
    public Collection<T> getAll() {
        return new ArrayList<>(tags);
    }

    @NotNull
    public String getCategory() {
        return category;
    }

    @SuppressWarnings("unchecked")
    public void join(@NotNull TagGroup other) {
        this.tags.addAll(other.tags);
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
