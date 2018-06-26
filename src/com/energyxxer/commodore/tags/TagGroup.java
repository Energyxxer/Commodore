package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;

import java.util.ArrayList;
import java.util.Collection;

public class TagGroup<T extends Tag> {
    private final Namespace namespace;
    private final String category;
    private final String directory;
    private final ArrayList<T> tags = new ArrayList<>();

    private final TagInstantiator<T> instantiator;

    public TagGroup(Namespace namespace, String category, String directory, TagInstantiator<T> instantiator) {
        this.namespace = namespace;
        this.category = category;
        this.directory = directory;
        this.instantiator = instantiator;
    }

    public T create(String name) {
        T existing = get(name);
        if(existing != null) return existing;

        T tag = instantiator.instantiate(this, this.namespace, name);

        this.tags.add(tag);

        return tag;
    }

    public T get(String name) {
        for(T value : tags) {
            if(value.getName().equals(name)) return value;
        }
        return null;
    }

    public Collection<T> getAll() {
        return new ArrayList<>(tags);
    }

    public String getCategory() {
        return category;
    }

    public void join(TagGroup other) {
        this.tags.addAll(other.tags);
    }

    public String getDirectoryName() {
        return directory;
    }

    @Override
    public String toString() {
        return namespace + "#" + category + ": " + tags;
    }
}
