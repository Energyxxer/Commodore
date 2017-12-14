package com.energyxxer.commodore.types;

import com.energyxxer.commodore.module.Namespace;

public abstract class Type {
    private Namespace namespace;
    private String name;

    public Type(Namespace namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    public boolean useNamespace() {
        return true;
    }

    public abstract boolean isConcrete();

    @Override
    public String toString() {
        return ((useNamespace()) ? (namespace.toString() + ':') : "") + name;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Type otherType = (Type) o;

        return (!useNamespace() || namespace.equals(otherType.namespace)) && name.equals(otherType.name);
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
