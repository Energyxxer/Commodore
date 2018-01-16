package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

import java.util.HashMap;

public class TypeDictionary<T extends Type> {

    private Namespace namespace;
    private String category;

    private HashMap<String, T> types = new HashMap<>();
    private TypeInstantiator<T> instantiator;

    public TypeDictionary(Namespace namespace, String category, TypeInstantiator<T> instantiator) {
        this.namespace = namespace;
        this.category = category;
        this.instantiator = instantiator;
    }

    public T create(String name) {
        T existing = types.get(name);
        if(existing != null) return existing;

        T newType = instantiator.create(name);
        types.put(name, newType);
        return newType;
    }

    public T get(String name) {
        T existing = types.get(name);
        if(existing != null) return existing;
        throw new TypeNotFoundException("'" + name + "' does not exist as '" + category + "' in the '" + namespace + "' namespace");
    }
}
