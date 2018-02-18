package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;

import java.util.ArrayList;
import java.util.Collection;

public class TagGroup<T extends Tag> {
    private final Namespace namespace;
    private final String groupName;
    private final ArrayList<T> tags = new ArrayList<>();

    private final TagInstantiator<T> instantiator;

    public TagGroup(Namespace namespace, String groupName, TagInstantiator<T> instantiator) {
        this.namespace = namespace;
        this.groupName = groupName;
        this.instantiator = instantiator;
    }

    public T createNew(String name) {
        T tag = instantiator.instantiate(this.namespace, name);

        this.tags.add(tag);

        return tag;
    }

    public Collection<T> getAll() {
        return new ArrayList<>(tags);
    }

    public String getGroupName() {
        return groupName;
    }

    public void join(TagGroup<T> other) {
        this.tags.addAll(other.tags);
    }
}
