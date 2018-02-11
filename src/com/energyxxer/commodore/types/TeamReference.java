package com.energyxxer.commodore.types;

public class TeamReference extends Type {

    public TeamReference(String name) {
        super(null, name);
    }

    @Override
    public boolean useNamespace() {
        return false;
    }

    @Override
    public boolean isConcrete() {
        return true;
    }
}
