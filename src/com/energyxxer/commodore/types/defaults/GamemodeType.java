package com.energyxxer.commodore.types.defaults;

import com.energyxxer.commodore.types.Type;

public class GamemodeType extends Type {
    public static final String CATEGORY = "gamemode";

    protected GamemodeType(String name) {
        super(CATEGORY, null, name);
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
