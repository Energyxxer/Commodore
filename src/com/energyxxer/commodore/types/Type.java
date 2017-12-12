package com.energyxxer.commodore.types;

import static com.energyxxer.commodore.CommandUtils.DEFAULT_NAMESPACE;
import static com.energyxxer.commodore.CommandUtils.NAMESPACE_ID_SEPARATOR;

public abstract class Type {
    private String namespace;
    private String name;

    public Type(String namespace, String name) {
        this.namespace = namespace;
        this.name = name;
    }

    public Type(String raw) {
        int separatorIndex = raw.indexOf(NAMESPACE_ID_SEPARATOR);

        if(separatorIndex >= 0) {
            this.namespace = raw.substring(0, separatorIndex);
            if(this.namespace.length() == 0) this.namespace = DEFAULT_NAMESPACE;
            this.name = raw.substring(separatorIndex + 1);
        } else {
            this.namespace = DEFAULT_NAMESPACE;
            this.name = raw;
        }
    }

    public boolean useNamespace() {
        return true;
    }

    public abstract boolean isConcrete();

    @Override
    public String toString() {
        return ((useNamespace()) ? (namespace + ':') : "") + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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
