package com.energyxxer.commodore.defpacks;

import java.util.HashMap;

public class DefinitionBlueprint {
    final String namespace;
    final String name;
    final HashMap<String, String> properties;

    DefinitionBlueprint(String name, HashMap<String, String> properties, boolean useNamespace) {
        if(useNamespace) {
            if(name.contains(":")) {
                this.namespace = name.substring(0, name.indexOf(":"));
                this.name = name.substring(name.indexOf(":")+1);
            } else {
                this.namespace = "minecraft";
                this.name = name;
            }
        } else {
            this.namespace = null;
            this.name = name;
        }
        this.properties = properties;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getProperties() {
        return new HashMap<>(properties);
    }

    @Override
    public String toString() {
        return "DefinitionBlueprint{" +
                "name='" + namespace + ':' + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
