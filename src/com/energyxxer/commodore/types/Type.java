package com.energyxxer.commodore.types;

import static com.energyxxer.commodore.CommandUtils.DEFAULT_NAMESPACE;
import static com.energyxxer.commodore.CommandUtils.NAMESPACE_ID_SEPARATOR;

public abstract class Type {
    private String namespace;
    private String id;

    public Type(String namespace, String id) {
        this.namespace = namespace;
        this.id = id;
    }

    public Type(String raw) {
        int separatorIndex = raw.indexOf(NAMESPACE_ID_SEPARATOR);

        if(separatorIndex >= 0) {
            this.namespace = raw.substring(0, separatorIndex);
            if(this.namespace.length() == 0) this.namespace = DEFAULT_NAMESPACE;
            this.id = raw.substring(separatorIndex + 1);
        } else {
            this.namespace = DEFAULT_NAMESPACE;
            this.id = raw;
        }
    }

    @Override
    public String toString() {
        return namespace + ':' + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type otherType = (Type) o;

        return namespace.equals(otherType.namespace) && id.equals(otherType.id);
    }

    @Override
    public int hashCode() {
        int result = namespace.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
