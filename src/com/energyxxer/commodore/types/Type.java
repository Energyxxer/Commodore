package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

import java.util.HashMap;

public abstract class Type {
    protected final String category;
    protected final Namespace namespace;
    protected final String name;
    protected final HashMap<String, String> properties = new HashMap<>();

    public Type(String category, Namespace namespace, String name) {
        this.category = category;
        this.namespace = namespace;
        this.name = name;
    }

    public boolean useNamespace() {
        return true;
    }

    public abstract boolean isConcrete();

    @Override
    public String toString() {
        return ((useNamespace()) ? (namespace + ":") : "") + name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Type otherType = (Type) o;

        return (!useNamespace() || namespace.equals(otherType.namespace)) && name.equals(otherType.name);
    }

    public String getName() {
        return name;
    }

    public void putProperty(String key, String value) {
        properties.put(key, value);
    }

    public void putProperties(HashMap<String, String> properties) {
        this.properties.putAll(properties);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public String getCategory() {
        return category;
    }
}
