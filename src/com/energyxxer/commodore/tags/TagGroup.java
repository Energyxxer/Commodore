package com.energyxxer.commodore.tags;

import com.energyxxer.commodore.module.Namespace;

import java.util.ArrayList;

public class TagGroup<T extends Tag> {
    private Namespace namespace;
    private String groupName;
    private ArrayList<T> tags = new ArrayList<>();

    private TagInstantiator<T> instantiator;

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
}
